// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.utils.SparkMax;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  
  public static class Drive {

  // Drive hardware 
  public static final SparkMax.ID LEFT_FRONT_DRIVE_MOTOR_ID = new SparkMax.ID(2, "DriveHardware/LeftFront/Drive");
  public static final SparkMax.ID LEFT_REAR_DRIVE_MOTOR_ID = new SparkMax.ID(3, "DriveHardware/LeftRear/Drive");
  public static final SparkMax.ID RIGHT_FRONT_DRIVE_MOTOR_ID = new SparkMax.ID(4, "DriveHardware/RightFront/Drive");
  public static final SparkMax.ID RIGHT_REAR_DRIVE_MOTOR_ID = new SparkMax.ID(5, "DriveHardware/RightRear/Drive");
  }
  
  public static class Global {
    public static final double ROBOT_LOOP_PERIOD = 1.0 / 60.0;

    // Motor RPMs, encoder values, and gear ratios
    public static final int NEO_MAX_RPM = 5880;
    public static final int NEO_ENCODER_TICKS_PER_ROTATION = 42;
    public static final int REV_ENCODER_TICKS_PER_ROTATION = 8192;
    public static final int SHOULDER_GEAR_RATIO = 480;
    public static final int ELBOW_GEAR_RATIO = 192;

  }

  public static class HID {
    public static final int PRIMARY_CONTROLLER_PORT = 0;
    public static final int SECONDARY_CONTROLLER_PORT = 1;
    public static final double CONTROLLER_DEADBAND = 0.10;
  }
}
