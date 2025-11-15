## RISC-V RV32I Five-Stage Core (SystemVerilog)

Cycle-accurate, in-order, single-issue RV32I core with classic 5-stage pipeline (IF, ID, EX, MEM, WB). Designed for clarity, educational value, and co-verification with the C++ simulator.

### Goals
- Implement RV32I (later RV32M) with real pipeline behavior: data/control hazards, forwarding, load-use stall, branch flush.
- Provide clean, synthesizable RTL (no vendor primitives) suitable for FPGA later.
- Co-sim with C++ pipeline simulator for architectural state and timing validation.

### Out of Scope (initial)
- MMU, caches, interrupts, privilege modes, debug module, complex branch prediction.

---

## Scope & Feature Roadmap

| Milestone | Feature Set |
|-----------|-------------|
| M0 | Single-cycle core ("scalar functional") executing a tiny ROM program (ADD/ADDI/BEQ) |
| M1 | Introduce pipeline registers, 5-stage flow without hazards resolved (insert manual NOPs) |
| M2 | Data forwarding (EX/MEM, MEM/WB), load-use stall unit |
| M3 | Branch/jump handling + flush (static not-taken) |
| M4 | Full RV32I load/store variants (LB/LH/LBU/LHU/SB/SH/SW) with sign/zero extension |
| M5 | CSR cycle/instret counters (optional) + simple ecall/ebreak trap signal |
| M6 | RV32M (MUL, MULH, DIV, REM) with multi-cycle EX unit + stall integration |
| M7 | Optional simple instruction/data memory latency modeling + ready/valid handshakes |

---

## Top-Level Architecture

### Modules (rtl/)
- `rv_core_top.sv`: Top-level wrapper: ports for clock, reset, instruction bus, data bus, debug.
- `if_stage.sv`: PC register, next-PC logic, instruction fetch handshake.
- `id_stage.sv`: Register file read, immediate generation, main decode, control signal gen.
- `regfile.sv`: 32×32 register array, x0 hardwired to zero.
- `hazard_unit.sv`: Detect load-use, branch flush, generate stall/forward select lines.
- `forward_unit.sv`: Compute forwarding selections for ALU operands.
- `alu.sv`: Arithmetic/logic/shifts/compare; later hooks for M extension.
- `muldiv_unit.sv`: Iterative multiplier/divider (when RV32M enabled).
- `mem_stage.sv`: Address alignment, byte-enable generation, load data formatting.
- `wb_stage.sv`: Writeback selection.
- `control_pkg.sv`: Enums, typedefs for opcodes, funct3/7, pipeline control structs.
- `csr_unit.sv` (later): cycle/instret counters (simple 64-bit up counters).

### Pipeline Registers
Struct-like bundles (SystemVerilog typedef) for:
- IF/ID: fetched instruction, PC
- ID/EX: decoded fields (rs1, rs2, rd, funct3, funct7, imm type), operand values, control bits
- EX/MEM: ALU result, branch decision, store data, control bits
- MEM/WB: load data (if any), final result, rd, control bits

Stored as `always_ff @(posedge clk)` blocks; flush logic sets them to NOP (encoded as ADDI x0,x0,0 or custom NOP code) when required.

### Control Signals (core)
- `regWrite`, `memRead`, `memWrite`, `memToReg`, `branch`, `jal`, `jalr`, `aluOp`, `useImm`, `isLoad`, `isStore`.

### Forwarding Priority
EX stage operand selection order: EX/MEM > MEM/WB > regfile.

### Load-Use Stall
If ID instruction needs rs1 or rs2 matching EX/MEM load destination that will produce data only after MEM, insert one bubble (stall IF/ID, inject NOP into ID/EX).

### Branch Handling
Static not-taken: fetch sequentially; when branch taken or JAL/JALR evaluated in EX, flush IF/ID and ID/EX (1-cycle penalty).

---

## Interfaces

### Instruction Bus (simple for M0–M4)
```
input  logic        clk, rst_n;
output logic [31:0] instr_addr;
input  logic [31:0] instr_rdata; // assumed valid same cycle (later: ready/valid)
```

### Data Bus
```
output logic [31:0] data_addr;
output logic [31:0] data_wdata;
output logic [3:0]  data_be;     // byte enables for SB/SH/SW
output logic        data_we;     // write enable
input  logic [31:0] data_rdata;  // read data
```

Future (latency modeling): add `instr_valid/instr_ready` and `data_valid/data_ready` handshake signals.

### Debug/Trace (optional Verilator instrumentation)
- Export pipeline register contents each cycle via DPI or hierarchical references.
- Provide `halt` signal when ecall encountered or max cycles reached.

---

## Verification Strategy

### Layers
1. Unit tests (Verilator + C++): ALU ops, hazard unit, forwarding unit.
2. Module tests: run short instruction sequences, observe expected register writes and PC changes.
3. Integration: execute hand-written assembly (add chain, load-use, branch taken/not) comparing against the C++ functional interpreter (M0) then pipeline simulator (timing checks).
4. ISA compliance: integrate `riscv-tests` RV32I subset; harness loads test binary, runs until signature region written.
5. Random instruction fuzz (bounded address space): generate random valid instructions; cross-check commit log vs C++ reference.

### Co-Simulation Hooks
- Verilator provides C++ API to step clock; C++ harness feeds instructions/memory responses.
- After each retired instruction (WB with regWrite or control transfer), compare architectural state (regfile + PC) to simulator.

### Waveform & Debug
- Basic `--trace` enable for VCD/FST; capture pipeline registers and key control signals.
- Annotated commit log: cycle, pc, instr, rd/value, events(stall, flush, forward).

---

## Directory Layout
```
rtl/
  core/ (rv_core_top.sv, stages, regfile)
  units/ (alu.sv, muldiv_unit.sv, hazard_unit.sv, forward_unit.sv)
  pkg/ (control_pkg.sv, isa_pkg.sv)
tb/
  simple/ (testbench_simple.sv)
  verilator/ (verilator_main.cpp)
scripts/ (build.sh, run_tests.sh)
tests/isa/ (assembly or ELF binaries)
docs/ (this file, waveforms, coverage notes)
```

---

## Coding Style & Guidelines
- Use `logic` over `wire/reg` (SystemVerilog). No implicit nets.
- One clock domain initially; synchronous active-low reset.
- Separate combinational (`always_comb`) and sequential (`always_ff`) logic.
- Parameterize data widths for future RV64I extension (e.g., `XLEN` localparam=32 now).
- Avoid latches (complete assignments in combinational blocks).

---

## Milestone Detail

### M0 (Single-Cycle Core)
- Implement fetch, decode, execute, memory access, writeback in one cycle.
- Add a tiny ROM (case statement or initial block) for first program.
- Validate ADDI, ADD, BEQ, JAL, LUI.

### M1 (Pipeline Skeleton)
- Introduce pipeline registers; handle NOP injection manually in test program (avoid hazards).
- Confirm correct instruction sequencing and register writes.

### M2 (Data Forwarding + Load-Use Stall)
- Implement forwarding paths and hazard detection; add directed tests (ADD chain, load-use).
- Measure CPI on microbenchmarks.

### M3 (Control Hazards)
- Branch decision in EX; flush earlier stages; test taken/not-taken sequences.

### M4 (Memory Semantics Complete)
- Byte/halfword loads/stores with proper sign/zero extend; unaligned access behavior defined (trap or ignore).

### M5 (Basic CSRs + ecall/ebreak)
- CSR cycle + instret increments; ecall sets halt flag.

### M6 (M Extension)
- Iterative multiplier/divider (state machine); stall pipeline while busy; verify division by zero per spec.

### M7 (Latency Modeling)
- Add ready/valid handshake; introduce artificial memory latency; measure impact on CPI.

---

## Edge Cases & Risk Mitigation
- x0 writes ignored: assert in regfile.
- JALR: ensure LSB(PC)=0 (mask bit0) per spec.
- Load-use detection: ensure no false positives on independent instructions.
- Multi-cycle MUL/DIV: avoid writeback conflicts; stall new issue until result ready.
- Alignment: define policy (trap vs allow) early to avoid inconsistent tests.

---

## Tooling
- Verilator for fast simulation + coverage (line/toggle). Optionally Icarus/ModelSim for waveform confirmation.
- FOSS RISC-V GCC toolchain to compile test ELFs; script extracts text/data sections into memory init.
- Python helper script to convert ELF to hex/mem file.

---

## Next Steps
1. Approve milestone plan and alignment with C++ simulator.
2. Scaffold `rtl/` with M0 single-cycle core + basic testbench.
3. Integrate Verilator build script and start directed tests.
4. Parallel: implement C++ M0 interpreter for cross-check.

If approved, I’ll generate the initial RTL skeleton and Verilator harness next.
