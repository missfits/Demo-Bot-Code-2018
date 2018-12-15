/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6418.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveStraight extends Command {
	private double speed, distance,encoderOffSet;
	public DriveStraight(double speed, double inches) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
		this.speed = speed;
		distance = inches;
		SmartDashboard.putNumber("Goal Distance: ", (distance/ 18.85) * 4096);
	}
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		encoderOffSet = Robot.driveTrain.getEncoderPosition();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.driveTrain.drive(speed);
		SmartDashboard.putNumber("Encoder distance: ", Math.abs(Robot.driveTrain.getEncoderPosition() - encoderOffSet));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return 	Math.abs(Robot.driveTrain.getEncoderPosition() - encoderOffSet) >= (distance/ 18.85) * 4096;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveTrain.stopDrive();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.driveTrain.stopDrive();
	}
}
