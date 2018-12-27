package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6418.robot.Robot;

/**
 * Command that spins intake wheels out
 */
public class ShootCube extends Command {
	private Timer timer = new Timer();

    /**
     * Constructor; declares requirements
     */
    public ShootCube() {
        requires(Robot.intake);
    }

    /**
     * Starts timer
     */
    protected void initialize() {
    	timer.start();
    }

    /**
     * Spins wheels
     * TODO: move to initialize() method?
     */
    protected void execute() {
    	Robot.intake.runWheels(0.7);
    }

    /**
     * Finishes when timer runs for over 1 second
     *
     * @return boolean of whether or not it is finished
     */
    protected boolean isFinished() {
        return timer.get() > 1.0;
    }

    /**
     * Stops wheels
     */
    protected void end() {
    	Robot.intake.stopWheels();
    }

    /**
     * Will stop wheels if command is interrupted
     */
    protected void interrupted() {
    	Robot.intake.stopWheels();
    }
}
