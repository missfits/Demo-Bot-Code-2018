package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6418.robot.Robot;
/**
 * Command that executes middle switch auto
 */
public class MiddleSwitch extends CommandGroup {
	double angle1, angle2;
	double turntDistance, distanceAfterTurn2;

	/**
	 * Constructor; executes auto strategy
	 */
    public MiddleSwitch() {
    	if (Robot.switchLeft() == -1)
    		// If system returns an error, do nothing
    		return;
    	else if (Robot.switchLeft() == 1) {
    		// If switch plate is on left, set vars so that robot executes middle -> left
    		angle1 = -34.5;
    		angle2 = 36;
    		turntDistance = 72;
    		distanceAfterTurn2 = 13;
    	} else if (Robot.switchLeft() == 0) {
    		// If switch plate is on right, set vars so that robot executes middle -> right
    		angle1 = 35;
    		angle2 = -37;
    		turntDistance = 70;
    		distanceAfterTurn2 = 13;
    	}

    	// Executes auto strategy
    	addSequential(new DriveStraight(0.5, 6));
    	addSequential(new Turn(angle1));
    	addSequential(new Pause(0.5));
    	addSequential(new DriveStraight(0.5, turntDistance));
    	addSequential(new Turn(angle2));
    	addSequential(new DriveStraight(0.5, distanceAfterTurn2));
    	addSequential(new ShootCube());
    	addSequential(new DriveStraight(-0.5, 6));
    }
}
