package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team6418.robot.Robot;
/**
 *
 */
public class MiddleSwitch extends CommandGroup {
	double angle1, angle2;
	double turntDistance, distanceAfterTurn2;
	
    public MiddleSwitch() {
    	if (Robot.switchLeft() == -1)
    		return;
    	else if (Robot.switchLeft() == 1) {
    		angle1 = -34.5;
    		angle2 = 36;
    		turntDistance = 72;
    		distanceAfterTurn2 = 13;
    	} else if (Robot.switchLeft() == 0) {
    		angle1 = 35;
    		angle2 = -37;
    		turntDistance = 70;
    		distanceAfterTurn2 = 13;
    	}
    	addSequential(new DriveStraight(0.5,6));
    	addSequential(new Turn(angle1));
    	addSequential(new Pause(0.5));
    	addSequential(new DriveStraight(0.5,turntDistance));
    	addSequential(new Turn(angle2));
    	addSequential(new DriveStraight(0.5,distanceAfterTurn2));
    	addSequential(new ShootCube());
    	addSequential(new DriveStraight(-0.5,6));
    }
}
