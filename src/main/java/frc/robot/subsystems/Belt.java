// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Belt extends SubsystemBase {
  /** Creates a new Belt. */

  public TalonSRX Belt_Motor_Lower = new TalonSRX(Constants.MOTOR_BELT_MAIN);
  public TalonSRX BELT_MOTOR_Upper = new TalonSRX(Constants.MOTOR_BELT_UPPER);


  public Belt(double speed) {

    //TODO set to test mode if wanting to follow

    //Belt_Motor.follow(BELT_MOTOR_UPPER);

    Belt_Motor_Lower.set(ControlMode.Current, speed);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
