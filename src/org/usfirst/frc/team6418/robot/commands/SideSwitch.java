package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SideSwitch extends CommandGroup {

    public SideSwitch() {
    	addSequential(new DriveStraight(0.5,65));
    	addSequential(new ShootCube());
    }
}
