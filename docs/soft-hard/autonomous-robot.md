## Autonomous Robot (ROS2 + MCU)

Small differential-drive robot with a microcontroller for low-level control and a ROS2 stack for high-level autonomy.

### Why it's cool
- Full robotics stack: control, state estimation, mapping, and integration.
- Bridges embedded firmware with modern middleware (ROS2).

### Hardware (example)
- Chassis: 2-wheel base with encoders + caster
- Motors: DC geared motors + motor driver (e.g., DRV8833/L298N class)
- MCU: STM32 or ESP32 (UART/USB); optional micro-ROS
- Sensors: IMU (MPU-6050/ICM-20948), ultrasonic or small lidar (RPLidar A1 class)
- Power: 2S Li-ion/LiPo with BMS and 5V regulator
- Host: Laptop or SBC (e.g., Raspberry Pi) running ROS2

### Software architecture
- Firmware (MCU): read encoders/IMU, run PID velocity control, expose UART protocol
- ROS2 nodes (host):
  - drivers: serial bridge to MCU; IMU driver
  - estimation: fuse wheel odom + IMU (ekf)
  - control: cmd_vel to wheel targets (PID on MCU)
  - navigation: teleop first; later simple waypoint follower

### Milestones
- M0: Teleop: ROS2 publishes cmd_vel; MCU drives wheels at target velocity
- M1: Odometry + IMU publishing; visualize in rviz
- M2: Closed-loop PID velocity control; parameter tuning
- M3: Simple waypoint following; basic obstacle avoidance (if lidar)

### Interfaces
- Serial protocol: fixed-size frames with header, payload, CRC
- ROS2 topics: /cmd_vel, /odom, /imu, /tf

### Risks
- Electrical noise on encoders and IMU; add filtering/grounding
- Timing drift between host and MCU; include timestamps and sync

### Next steps
- If chosen, I’ll add a minimal firmware skeleton and ROS2 package layout with interface docs.
