/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6418.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6418.robot.commands.*;
import org.usfirst.frc.team6418.robot.subsystems.*;

enum StartingPosition {
	LEFT, MIDDLE, RIGHT
}

enum AutoStrategy {
	STRAIGHT, SWITCH, SCALE, NOTHING
}

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static DriveTrain driveTrain = new DriveTrain();
	public static Intake intake = new Intake();
	public static OI oi;
	
	public static int switchLeft() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				return 1;
			} else {
				return 0;
			}
		}
		return -1;
	}
	
	public static int scaleLeft() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0) {
			if (gameData.charAt(1) == 'L') {
				return 1;
			} else {
				return 0;
			}
		}
		return -1;
	}

	Command autonomousCommand;
	SendableChooser<AutoStrategy> autoStrategy = new SendableChooser<>();
	SendableChooser<StartingPosition> startPosition = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		autoStrategy.addObject("Only Straight", AutoStrategy.STRAIGHT);
		autoStrategy.addObject("Switch", AutoStrategy.SWITCH);
		autoStrategy.addDefault("Fancy Scale", AutoStrategy.SCALE);
		autoStrategy.addObject("Do Nothing", AutoStrategy.NOTHING);
		SmartDashboard.putData("Auto Strategy", autoStrategy);
		
		startPosition.addDefault("Left", StartingPosition.LEFT);
		startPosition.addObject("Middle", StartingPosition.MIDDLE);
		startPosition.addObject("Right", StartingPosition.RIGHT);
		SmartDashboard.putData("Starting Position", startPosition);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = null;
		switch (autoStrategy.getSelected()) {
			case SWITCH:
				if ((Robot.switchLeft() == 1 && startPosition.getSelected() == StartingPosition.LEFT)
						|| (Robot.switchLeft() == 0 && startPosition.getSelected() == StartingPosition.RIGHT)) {
					// Does side switch if robot is on same side as switch plate
					autonomousCommand = new SideSwitch();
				} else if (startPosition.getSelected() == StartingPosition.MIDDLE) {
					// Does middle switch if robot is in the middle
					autonomousCommand = new MiddleSwitch();
				} else {
					// Drives straight if robot is on side opposite to switch plate
					autonomousCommand = new DriveStraight(0.3,85);
				}
				break;
			case SCALE:
				if ((Robot.scaleLeft() == 1 && startPosition.getSelected() == StartingPosition.LEFT)
						|| (Robot.scaleLeft() == 0 && startPosition.getSelected() == StartingPosition.RIGHT)) {
					// Does same side scale if robot is on same side as scale plate
					// TODO: scale auto true
				} else if ((Robot.scaleLeft() == 1 && startPosition.getSelected() == StartingPosition.RIGHT)
						|| (Robot.scaleLeft() == 0 && startPosition.getSelected() == StartingPosition.LEFT)) {
					// Does opposite side scale if robot is on side opposite to scale plate
					// TODO: scale auto false
				} else if (startPosition.getSelected() == StartingPosition.MIDDLE) {
					// Does middle switch if robot is in the middle
					autonomousCommand = new MiddleSwitch();
				} else {
					// Drives straight if something glitches
					autonomousCommand = new DriveStraight(0.3,85);
				}
				break;
			case STRAIGHT:
				// Drives straight
				autonomousCommand = new DriveStraight(0.3,85);
				break;
			default:
				// TODO: change default to drive straight?
				// Do nothing if something glitches
				autonomousCommand = new Pause(15.0);
				break;
		}
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putData(intake);
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
