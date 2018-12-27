package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6418.robot.Robot;
import org.usfirst.frc.team6418.robot.commands.TiltIntake.TiltPosition;
import org.usfirst.frc.team6418.robot.commands.IntakeHug.HugPosition;

public class Teleop extends Command {
    /**
     * Constructor; declares requirements
     */
    public Teleop() {
        requires(Robot.driveTrain);
        requires(Robot.intake);
    }

    /**
     * Called just before this Command runs the first time
     */
    protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	// Intake
        if (Robot.oi.leftTriggerPressed()) {
            Robot.intake.runWheels(0.8);
        } else if (Robot.oi.rightTriggerPressed()) {
            Robot.intake.runWheels(-0.8);
        } else {
            Robot.intake.runWheels(0);
        }
    	/*
    	Robot.oi.xButton.whenPressed(new TiltIntake(TiltPosition.UP));
    	Robot.oi.bButton.whenPressed(new TiltIntake(TiltPosition.DOWN));
    	Robot.oi.leftBumperButton.whenPressed(new IntakeHug(HugPosition.HUG));
    	Robot.oi.rightBumperButton.whenPressed(new IntakeHug(HugPosition.UNHUG));
    	*/

    	// Drivetrain
    	if (Math.abs(Robot.oi.rightStickX()) > 0.4) {
    	    // Controls strafe
    		double b = -2.0/3.0;
			if (Robot.oi.rightStickX() < 0) {
				b *= -1;
			}
    		Robot.driveTrain.strafe(5 * Robot.oi.rightStickX() / 3 + b);
    	} else {
    	    // Controls normal tank drive
    		Robot.driveTrain.tankDrive(-Robot.oi.leftStickY(),-Robot.oi.rightStickY());
    	}

        SmartDashboard.putNumber("Left Stick Y", Robot.oi.leftStickY());
        SmartDashboard.putNumber("Right Stick Y", Robot.oi.rightStickY());
    }

    /** Make this return true when this Command no longer needs to run execute()
     *
     * @return whether or not Command should finish
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called once after isFinished returns true
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
