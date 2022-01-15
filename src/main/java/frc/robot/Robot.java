// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoCommand;
import frc.robot.limelight.LLControllers;
import frc.robot.limelight.LL_Update_Tracking;
import frc.robot.limelight.LLdriveControl;
import frc.robot.limelight.LLmain;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.cscore.VideoSource;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Command m_autonomousCommand;

  public static DriveTrain driveTrain = new DriveTrain();
  public static LLdriveControl lldriveControl = new LLdriveControl();
  public static LLmain llmain = new LLmain();
  public static LLControllers llcontrollers = new LLControllers();
  public static BallIntake ballIntake = new BallIntake(Constants.MOTOR_BALLINTAKE_SPEED);
  public static Belt belt = new Belt(Constants.MOTOR_BELT_SPEED_LOWER);
  public static ChainAndHook chainAndHook = new ChainAndHook();
  public static Hopper hopper = new Hopper(Constants.HOPPER_SPEED);
  public static Tailgate tailgate = new Tailgate();

  public static RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    //Limelight is a studdddd

    LL_Update_Tracking.Tracking();

        llcontrollers.controllers(0.0, 0.0);

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  public static UsbCamera usbCamera1 = null;
  public class CameraThread extends Thread {
    final int CAMERA1 = 0;
   // private final int currentCamera = CAMERA1;   // UNCOMMENT WHEN RUNNING THE PROGRAM THRU ROBORIO!!!!

    VideoSink server;
    
    public void run(){
        System.out.println("CameraThread running");

     }

     public void setResolutionLow(){
        System.out.println("CameraThread rsetResolutionLow running");
        usbCamera1.setResolution(150, 150);
        usbCamera1.setFPS(10);

    }

    public void setResolutionHigh(){
        System.out.println("CameraThread rsetResolutionHigh running");
        usbCamera1.setResolution(200, 200);
        usbCamera1.setFPS(20);
    }

    public void setCameraSource(){
        System.out.println("CameraThread setCameraSource running");
        server.setSource(usbCamera1);
        SmartDashboard.putString("My Key", "Hello");
    }

    public void getCameraConfig(){
        System.out.println("CameraThread getPrintCameraConfig running");
        String cameraConfig; 
        cameraConfig = usbCamera1.getConfigJson();
        if (cameraConfig.isEmpty() == false) {
            System.out.println(cameraConfig.toString()); //print to console
        }
    }

    public void setCameraConfig(){
        System.out.println("CameraThread setPrintCameraConfig running");

        
        usbCamera1.setFPS(20);
        usbCamera1.setBrightness(50);  
        usbCamera1.setExposureAuto();  
  }
  }

  public static CameraThread myCameraThread;
 
  @Override
  public void robotInit() {

    m_robotContainer = new RobotContainer();

   // llmain.LL_Data();

    myCameraThread = new CameraThread();
    CameraServer.getInstance();
    usbCamera1 = CameraServer.startAutomaticCapture(myCameraThread.CAMERA1);
    CameraServer.getInstance();
    myCameraThread.server = CameraServer.getServer();

    usbCamera1.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);


    myCameraThread.setCameraConfig();
 
    myCameraThread.start();
    myCameraThread.setResolutionHigh();
    myCameraThread.getCameraConfig();

    m_autonomousCommand = (new AutoCommand());

  }
}
