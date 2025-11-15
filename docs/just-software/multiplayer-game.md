## Multiplayer Game (Server-Authoritative, C++)

A small real-time multiplayer game where the server is authoritative and clients use prediction/interpolation.

### Why it's cool
- Teaches networking, latency compensation, and deterministic simulation.
- Transferable to any online game or simulation backend.

### Scope (MVP)
- Top-down arena with player movement and projectiles
- Dedicated server (C++), lightweight client (could be C++ or later web)
- UDP with reliability layer or QUIC; simple auth/handshake

### Tech stack
- C++20, CMake
- asio or Boost.Asio (networking)
- Serialization: FlatBuffers or Protobuf
- Tests: Catch2/GoogleTest for sim and protocol

### Architecture sketch
- common/: protocol definitions, ids, serialization
- server/: headless loop, world state, input queue, snapshot/rollback
- client/: prediction, interpolation, input capture
- net/: sockets, reliability, time sync

### Milestones
- M0: UDP handshake; client sends inputs; server echos back
- M1: Deterministic movement sim on server; client predicts + interp
- M2: Spawn/Despawn; simple projectiles; hit detection server-side
- M3: Snapshot delta compression; basic anti-cheat checks

### Latency strategies
- Client-side prediction for movement
- Server reconciliation using input sequence numbers
- Interpolation buffer for other players (100–150ms)

### Risks
- Nondeterminism from floating point: prefer fixed-step and fixed-point or careful use of floats
- Packet loss/out-of-order handling; build a minimal reliability layer

### Next steps
- If chosen, I’ll scaffold common/, server/, client/ with a small echo protocol and tests.
