## RISC-V RV32I Five-Stage Pipeline Simulator (C++)

A cycle-accurate, in-order, single-issue simulator that models a classic 5-stage pipeline: IF → ID → EX → MEM → WB. Starts with RV32I, grows to RV32IM, with optional visualization and debugging.

### Goals
- Behave like a simple real CPU: per-cycle pipeline state, stalls, forwarding, and flushes.
- Run binaries (ELF) compiled with a standard RISC-V toolchain.
- Provide clear traces, debugging, and performance metrics (CPI, stall counts).

### Out of scope (initially)
- Out-of-order, superscalar, caches/TLB/virtual memory, privileged S-mode/M-mode.

---

## Scope and Features

### ISA and features (MVP → Extensions)
- MVP: RV32I base integer set: arithmetic, logical, shifts, branches, jumps, loads/stores, LUI/AUIPC, system (ecall/ebreak as traps).
- Extensions (later): RV32M (mul/div), basic CSRs (cycle, time, instret), simple traps/exceptions.

### Pipeline model
- 5 pipeline registers: IF/ID, ID/EX, EX/MEM, MEM/WB, plus PC.
- Hazard handling:
  - Data hazards: forwarding from EX/MEM and MEM/WB; stall on load-use if needed.
  - Control hazards: static branch prediction (not-taken), flush on taken branch/jump.
  - Structural hazards: avoided by design (separate units), but can be modeled.
- Memory: flat little-endian memory; configurable latency for data and instruction memory (start as 1-cycle).

### Extras (stretch but practical)
- ELF loader for RV32 binaries.
- CLI debugger: step by cycle or instruction, breakpoints on PC, watchpoints on memory, register/memory dump.
- Timeline trace: per-cycle JSONL or table showing each stage’s instruction and bubbles.
- Simple visualizer (optional): ImGui to render a pipeline timeline.
- Config file (YAML/TOML): memory size, latencies, initial PC, trace options.

---

## Architecture

### Modules
- core/cpu.hpp, cpu.cpp: top-level step(), run(), counters, reset.
- core/pipeline.hpp: pipeline register structs (IFID, IDEX, EXMEM, MEMWB) and control flags.
- decode/decoder.hpp, decoder.cpp: instruction parsing to an IR (enum opcode, funct3/7, imm forms).
- exec/alu.hpp: ALU ops, branch compare, shifters.
- core/hazard.hpp: hazard detection and forwarding logic.
- mem/memory.hpp: memory interface; backing store (vector or sparse pages); load/store helpers with sign-extend.
- io/elf_loader.hpp: parse ELF32 and load to memory; set entry point.
- debug/trace.hpp: per-cycle trace objects; JSONL writer.
- cli/main.cpp: argument parsing, run loop, debug shell.

### Data structures
- regfile[32] uint32_t; x0 is hardwired to 0 (writes ignored).
- PC uint32_t; next_pc computed each cycle.
- Instruction IR: { opcode, funct3, funct7, rd, rs1, rs2, imm, type }.
- Pipeline regs hold: decoded IR, PC, operand values, control signals (memRead, memWrite, regWrite, memToReg, branch, etc.).

### Cycle flow (high level)
1) WB: write MEM/WB result to regfile (if regWrite and rd != x0).
2) MEM: perform load/store; compute mem result.
3) EX: ALU ops, branch target, branch decision; generate forwarding sources.
4) ID: decode, read register file; hazard detect to insert bubble if needed.
5) IF: fetch next instruction (respect stalls/flushes); update PC.

Forwarding priority: EX/MEM over MEM/WB. Stall on load-use when needed.

Pseudo for one cycle (sketch):
```
// 1) Writeback
if (MEMWB.regWrite && MEMWB.rd != 0) reg[MEMWB.rd] = MEMWB.memToReg ? MEMWB.memData : MEMWB.aluResult;

// 2) Memory
auto memOut = MEM_stage(EXMEM, memory);

// 3) Execute
auto exOut = EX_stage(IDEX, forwardFrom= {EXMEM, MEMWB});

// 4) Decode
auto idOut = ID_stage(IFID, reg, hazards, willStall);

// 5) Fetch
auto ifOut = IF_stage(pc, imem, willStall, willFlush, newPC);

// Latch pipeline regs considering stall/flush rules
```

---

## Interfaces

### CLI
- riscv-sim run --elf program.elf [--trace pipeline.jsonl] [--max-cycles N] [--mem 64K] [--latency-d 1] [--latency-i 1]
- riscv-sim debug --elf program.elf (interactive REPL)

### Trace format
- JSON Lines (one per cycle): { cycle, pc, if:{pc,inst}, id:{...}, ex:{...}, mem:{...}, wb:{...}, events:["stall","flush"], counters:{cpi,...} }

### Config
- YAML/TOML: memory_size, i_latency, d_latency, prediction: not_taken|always_taken|bht1, trace: enabled, sample_rate.

---

## Testing and Validation

### Strategy
- Unit tests: decoder (all formats), ALU ops, load/store sign/zero-extend, hazard unit (targeted cases).
- Integration: run small assembly snippets to validate forwarding and stalls.
- Differential testing: compare against a simple functional (single-cycle) interpreter for architectural state after each instruction (ignoring timing).
- Compliance: integrate riscv-tests RV32I where feasible; note: some tests expect certain CSRs or environment calls.

### Edge cases to cover
- x0 writes ignored; sign extension on LB/LH; JALR lower bit cleared (bit0=0); AUIPC/LUI immediate handling.
- Misaligned access: initially trap or emulate; document behavior.
- Load-use hazard stall; store-data forwarding (from EX or MEM/WB).
- Branch flush timing: exactly one bubble after taken branch (with static not-taken predictor).
- Division by zero (when M extension added): spec behavior.

---

## Milestones

- M0: Functional interpreter (no pipeline) for RV32I; run a few hand-written programs; defines ISA correctness baseline.
- M1: Pipeline skeleton and pipeline regs; run NOPs and ADD sequences with forwarding disabled; confirm clocking.
- M2: Implement forwarding and hazard detection; pass targeted data-hazard tests (add-add, load-use, store-use).
- M3: Branch/jump handling + flush; static not-taken predictor; validate with branch-heavy microbenchmarks.
- M4: Memory semantics (LB/LH/LBU/LHU/SB/SH/SW), configurable latencies; pass memory tests.
- M5: ELF loader + CLI run; per-cycle JSON trace; compute CPI and stall breakdown.
- M6: Optional UI visualizer (ImGui) for pipeline timeline; breakpoints/watchpoints.
- M7: RV32M extension and minimal CSRs (cycle, instret); basic ecall/ebreak traps.

---

## Implementation Notes

### Language/Build
- C++20, CMake; tests with Catch2/GoogleTest; clang-format/clang-tidy.

### Directory layout
```
sim/
  core/
  decode/
  exec/
  mem/
  io/
  debug/
  cli/
tests/
third_party/
```

### Performance tips
- Keep pipeline registers POD and contiguous.
- Avoid virtual dispatch in the hot path; prefer enums/switch and small structs.
- Consider a sparse page memory to keep address checks cheap and large memories feasible.

---

## Next Steps
1) Confirm MVP: RV32I + ELF + static not-taken + forwarding + load-use stall + 1-cycle memories.
2) I’ll scaffold the project (CMake, src layout above) with M0 functional interpreter and a tiny test.
3) Add one sample program (sum array) and validate interpreter vs pipeline as we implement milestones.
