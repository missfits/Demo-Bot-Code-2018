package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6418.robot.Robot;
/**
 *
 */
public class ShootCube extends Command {
	private Timer timer = new Timer();
    public ShootCube() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.runWheels(0.7);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 1.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stopWheels();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stopWheels();
    }
}
