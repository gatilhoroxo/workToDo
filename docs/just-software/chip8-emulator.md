## CHIP-8 Emulator (C++)

A classic entry emulator: interpret CHIP-8 bytecode, draw to a 64×32 display, map keypad, and support timers.

### Why it's cool
- Teaches CPU emulation, instruction decoding, timers, and graphics blitting.
- Quick wins: many public ROMs exist for testing.

### Scope (MVP)
- Load ROM; 4K memory space; 16 registers; PC, I; stack; delay/sound timers
- 64×32 monochrome display; keypad mapping to keyboard
- Cycle loop with correct timing; simple sound beep

### Tech stack
- C++17, CMake
- SDL2 for window/input/audio

### Architecture sketch
- chip8/cpu.hpp, cpu.cpp: registers, opcode fetch/decode/execute
- chip8/memory.hpp: RAM and stack
- chip8/timers.hpp: delay/sound timers at 60Hz
- platform/sdl_app.cpp: display, input, audio glue

### Milestones
- M0: CPU structure + 00E0/00EE/1NNN opcodes; clear screen and jump
- M1: Draw sprites (Dxyn), collision flag; keypad input
- M2: Timers and sound; load multiple ROMs; pause/reset
- M3: Debugger: step/run, register/memory view, breakpoint

### Validation
- Use known test ROMs (BC_test, IBM logo) and compare expected screen frames.

### Optional soft-hard extension
- Port to RP2040/ESP32 with small OLED and keypad; same core, new platform layer.

### Next steps
- If chosen, I’ll scaffold src/ with cpu/memory/timers and an SDL app entrypoint.
