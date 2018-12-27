package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Does nothing for specified amount of time
 */
public class Pause extends Command {
	private Timer timer = new Timer();
	private double time;

    /**
     * Constructor; sets class variable time to specified time in s
     *
     * @param time number of seconds to pause
     */
    public Pause(double time) {
        this.time = time;
    }

    /**
     * Starts timer
     */
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    /**
     * Finishes when timer reaches specified time
     *
     * @return boolean of whether or not it is finished
     */
    protected boolean isFinished() {
        return timer.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
