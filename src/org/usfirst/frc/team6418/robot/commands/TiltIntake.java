package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6418.robot.Robot;

/**
 * Command that tilts the intake up and down
 */
public class TiltIntake extends Command {
	public enum TiltPosition {
		DOWN, UP
	}
	
	private TiltPosition pos;

    /**
     * Constructor; sets class variable to specified position
     *
     * @param pos param that specifies position to tilt intake at (either UP or DOWN)
     */
    public TiltIntake(TiltPosition pos) {
        // requires(Robot.intake);
		this.pos = pos; 
    }

    /**
     * Tilts intake
     */
    protected void initialize() {
    	System.out.println("tilting");
    	switch (pos) {
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

    /**
     * finishes after first execution
     *
     * @return true
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * Debug statement
     * TODO: remove?
     */
    protected void end() {
    	System.out.println("tilt ended");
    }

    /**
     * Prints out message if command is interrupted
     */
    protected void interrupted() {
    	System.out.println("tilt interrupted");
    }
}
