// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tailgate extends SubsystemBase {
  /** Creates a new Tailgate. */
  public enum TailgateDirection{kOut, kIn};
  
  private Solenoid TailgateSet;

  public Tailgate() {
    TailgateSet = new Solenoid(0, null, 1);
    addChild("TailgateSet", TailgateSet);
  }

  public void TailgateSoleniod(TailgateDirection direction) {
    switch(direction) {
      case kOut:
      TailgateSet.set(true);
        break;
      case kIn:
      TailgateSet.set(false);
        break;
    }
  }
}
