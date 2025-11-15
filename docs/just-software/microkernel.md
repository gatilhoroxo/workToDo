## Hobby Microkernel (C/C++ + a bit of ASM)

Tiny kernel to learn boot, interrupts, memory management, and basic tasking.

### Why it's cool
- Demystifies how OSes start and manage hardware.
- Teaches linker scripts, paging, context switching, and drivers at a high level.

### Scope (MVP)
- Boot via GRUB (Multiboot) or UEFI; print to VGA/serial
- IDT/GDT setup, basic interrupt/exception handling (timer, keyboard)
- Physical/virtual memory manager (paging), simple heap
- Cooperative tasks (later: preemptive with PIT/APIC)

### Tech stack
- CMake toolchain for cross-compilation (x86_64-elf)
- C/C++ with NASM or GAS; QEMU for running; GDB for debug

### Layout sketch
- boot/: multiboot header, entry, linker script
- kernel/: main, interrupts, memory, scheduler, drivers (tty)
- lib/: minimal runtime helpers

### Milestones
- M0: Boot to "Hello from kernel" on QEMU serial and VGA text
- M1: IDT/GDT; handle page fault and timer IRQ; keyboard echo
- M2: Paging and a bump allocator; later a simple malloc
- M3: Cooperative tasks + context switch; basic syscall stub

### Risks
- Toolchain friction; we’ll pin versions and provide make targets
- Subtle ABI/calling-convention issues between C++ and ASM

### Next steps
- If chosen, I’ll scaffold the cross toolchain config and a QEMU run target.
