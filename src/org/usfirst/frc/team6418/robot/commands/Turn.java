package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6418.robot.Robot;

/**
 *
 */
public class Turn extends Command {
	private double angle;
    public Turn(double a) {
        requires(Robot.driveTrain);
        angle = a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.zeroGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.turn(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.driveTrain.getAngle()) >= Math.abs(angle);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.stopDrive();
    }
}
