package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6418.robot.Robot;

public class DriveStraight extends Command {
	private double speed, distance,encoderOffSet;

	/**
	 * Constructor; sets class variables speed and inches to specified doubles
	 *
	 * @param speed desired speed of robot
	 * @param inches desired distance for robot to drive
	 */
	public DriveStraight(double speed, double inches) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);

		this.speed = speed;
		distance = inches;
		SmartDashboard.putNumber("Goal Distance: ", (distance/ 18.85) * 4096);
	}

	// Called just before this Command runs the first time

	/**
	 * Stores current encoder position to calculate difference from
	 */
	@Override
	protected void initialize() {
		encoderOffSet = Robot.driveTrain.getEncoderPosition();
	}

	/**
	 * Drives forward and displays distance driven on SmartDashboard
	 */
	@Override
	protected void execute() {
		Robot.driveTrain.drive(speed);
		SmartDashboard.putNumber("Encoder distance: ", Math.abs(Robot.driveTrain.getEncoderPosition() - encoderOffSet));
	}

	/**
	 * Finishes when distance driven reaches distance set by user
	 *
	 * @return boolean of whether or not it is finished
	 */
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.driveTrain.getEncoderPosition() - encoderOffSet) >= (distance / 18.85) * 4096;
	}

	/**
	 * Stops driving robot
	 */
	@Override
	protected void end() {
		Robot.driveTrain.stopDrive();
	}

	/**
	 * If command is interrupted, will stop driving
	 */
	@Override
	protected void interrupted() {
		Robot.driveTrain.stopDrive();
	}
}
