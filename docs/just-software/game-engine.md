## Minimal Game Engine (C++)

A compact 2D engine to learn modern C++ architecture: ECS, rendering, input, audio, and basic tools.

### Why it's cool
- Builds real engine fundamentals you can reuse for games or simulations.
- Great playground for performance, data-oriented design, and API design.

### Scope (MVP)
- Window + renderer (SDL2 or SFML)
- Entity-Component-System (entt) for gameplay data
- Input handling, sprite rendering, simple audio (SFX)
- Scene loading from JSON/TOML

### Tech stack
- C++17/20, CMake
- SDL2 or SFML (windowing/audio), glm (math), entt (ECS)
- Optional: Lua (sol2) for scripting; ImGui for a tiny editor

### Architecture sketch
- core/: Engine loop, time, config
- ecs/: Components, systems, registry (entt)
- render/: Sprite batcher, textures, camera
- io/: Assets, JSON/TOML config
- game/: Example scene and systems

### Milestones
- M0: Window + render a sprite; WASD moves it; quit on ESC
- M1: ECS components (Transform, Sprite); systems (Render, Input)
- M2: Simple audio; sprite atlas; basic collision AABB
- M3: Scene file load; hot-reload asset; tiny UI via ImGui

### Risks and edges
- Event loop jitter; vsync vs fixed-step update
- Texture lifetime and batching correctness
- Data ownership in ECS (avoid raw pointers across registries)

### Next steps
- If chosen, I’ll scaffold: CMake, src/, third_party/, a demo scene, and a small test.
