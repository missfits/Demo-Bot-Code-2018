package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6418.robot.Robot;
/**
 *
 */
public class IntakeHug extends Command {
	
	public enum HugPosition{
		HUG, UNHUG
	}
	
	private HugPosition pos;
	
	public IntakeHug(HugPosition pos) {
		//requires(Robot.intake);
		this.pos = pos;
	}
	
    public IntakeHug() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(pos) {
    		case HUG:
    			Robot.intake.hug();
    			break;
    		case UNHUG:
    			Robot.intake.unHug();
    			break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
