package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6418.robot.Robot;

/**
 * Rotates the robot a specified angle w/ center of rotation at center of robot
 */
public class Turn extends Command {
	private double angle;

    /**
     * Constructor; declares requirements and stores angle value in class variable
     *
     * @param a desired angle of rotation
     */
    public Turn(double a) {
        requires(Robot.driveTrain);
        angle = a;
    }

    /**
     * Zeroes the gyro
     */
    protected void initialize() {
    	Robot.driveTrain.zeroGyro();
    }

    /**
     * Turns specified angle
     * TODO: move this to initialize() method?
     */
    protected void execute() {
    	Robot.driveTrain.turn(angle);
    }

    /**
     * Finishes when robot has turned enough
     *
     * @return boolean of whether or not it is finished
     */
    protected boolean isFinished() {
        return Math.abs(Robot.driveTrain.getAngle()) >= Math.abs(angle);
    }

    /**
     * Stops drive train
     */
    protected void end() {
    	Robot.driveTrain.stopDrive();
    }

    /**
     * Stops drive train if command is interrupted
     */
    protected void interrupted() {
    	Robot.driveTrain.stopDrive();
    }
}
