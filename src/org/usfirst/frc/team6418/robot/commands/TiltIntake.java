package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6418.robot.Robot;

/**
 *
 */

public class TiltIntake extends Command {
	
	public enum TiltPosition {
		DOWN, UP
	}
	
	private TiltPosition pos;
	
    public TiltIntake(TiltPosition pos) {
        //requires(Robot.intake);
		this.pos = pos; 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("tilting");
    	switch(pos) {
    		case UP:
    			Robot.intake.tiltUp();
    			break;
    		case DOWN:
    			Robot.intake.tiltDown();
    			break;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("tilt ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("tilt interrupted");
    }
}
