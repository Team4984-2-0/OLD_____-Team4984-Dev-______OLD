// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class BallIntake extends SubsystemBase {
  /** Creates a new Intake. */

  public TalonSRX ballIntakeMotor_left = new TalonSRX(Constants.MOTOR_INTAKE_LEFT);
  public TalonSRX ballIntakeMotor_Right = new TalonSRX(Constants.MOTOR_INTAKE_RIGHT);

  

  public BallIntake(double speed) {

    ballIntakeMotor_left.follow(ballIntakeMotor_Right);
    ballIntakeMotor_Right.set(ControlMode.Current, speed);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
