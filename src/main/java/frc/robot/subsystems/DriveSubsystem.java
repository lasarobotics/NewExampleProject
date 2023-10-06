// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Import the things you need
// CTRL+SHIFT+P -> Organize Imports if VSCode gives you errors
package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.SparkMax;

public class DriveSubsystem extends SubsystemBase {

  // Initializes hardware, including boolean isHardwareReal for simulations
  public static class Hardware {
    private boolean isHardwareReal;
    private SparkMax lMasterMotor, rMasterMotor;
    private SparkMax lSlaveMotor, rSlaveMotor;
    private AHRS navx;

    public Hardware(boolean isHardwareReal,
        SparkMax lMasterMotor,
        SparkMax rMasterMotor,
        SparkMax lSlaveMotor,
        SparkMax rSlaveMotor,
        AHRS navx) {
      this.isHardwareReal = isHardwareReal;
      this.lMasterMotor = lMasterMotor;
      this.rMasterMotor = rMasterMotor;
      this.lSlaveMotor = lSlaveMotor;
      this.rSlaveMotor = rSlaveMotor;
      this.navx = navx;
    }
  }
  // Initializes motors, drivetrain object, and navx
  private SparkMax m_lMasterMotor, m_lSlaveMotor;
  private SparkMax m_rMasterMotor, m_rSlaveMotor;

  DifferentialDrive m_drivetrain;

  private AHRS m_navx;

  /**
   * Create an instance of DriveSubsystem
   * <p>
   * NOTE: ONLY ONE INSTANCE SHOULD EXIST AT ANY TIME!
   * <p>
   * 
   * @param drivetrainHardware   Hardware devices required by drivetrain
   */
  public DriveSubsystem(Hardware drivetrainHardware) {

    // Instantiates motors and navx
    this.m_lMasterMotor = drivetrainHardware.lMasterMotor;
    this.m_rMasterMotor = drivetrainHardware.rMasterMotor;
    this.m_lSlaveMotor = drivetrainHardware.lSlaveMotor;
    this.m_rSlaveMotor = drivetrainHardware.rSlaveMotor;

    this.m_navx = drivetrainHardware.navx;

    // Sets master motors inverted
    m_rMasterMotor.setInverted(true);
    m_lMasterMotor.setInverted(true);

    // Makes slaves follow masters
    m_lSlaveMotor.follow(m_lMasterMotor);
    m_rSlaveMotor.follow(m_rMasterMotor);

    // Creates differential drive object with master motors as parameters
    m_drivetrain = new DifferentialDrive(m_lMasterMotor, m_rMasterMotor);
  }

  /**
   * Initialize hardware devices for drive subsystem
   * 
   * @return hardware object containing all necessary devices for this subsystem
   */
  public static Hardware initializeHardware(boolean isHardwareReal) {
    Hardware drivetrainHardware = new Hardware(
      isHardwareReal,
      new SparkMax(Constants.Drive.LEFT_FRONT_DRIVE_MOTOR_ID, MotorType.kBrushless),
      new SparkMax(Constants.Drive.RIGHT_FRONT_DRIVE_MOTOR_ID, MotorType.kBrushless),
      new SparkMax(Constants.Drive.LEFT_REAR_DRIVE_MOTOR_ID, MotorType.kBrushless),
      new SparkMax(Constants.Drive.RIGHT_REAR_DRIVE_MOTOR_ID, MotorType.kBrushless),
      new AHRS(SPI.Port.kMXP)
    );

    return drivetrainHardware;
  }

  // Controls the robot during teleop
  public void teleop(double speed, double turn) {
    m_drivetrain.arcadeDrive(speed, turn);
  }

  // Controls the robot during teleop under special conditions
  public void teleopAdvanced(double speed, double turn) {
    m_lMasterMotor.set(speed + turn);
    m_rMasterMotor.set(speed - turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation (for tests)
  }
}
