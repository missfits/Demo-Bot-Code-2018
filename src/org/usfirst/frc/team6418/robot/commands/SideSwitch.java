package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command that executes side switch auto
 */
public class SideSwitch extends CommandGroup {
    /**
     * Constructor; executes side switch auto
     * TODO: change so it deposits on outside edge of switch
     */
    public SideSwitch() {
    	addSequential(new DriveStraight(0.5,65));
    	addSequential(new ShootCube());
    }
}
