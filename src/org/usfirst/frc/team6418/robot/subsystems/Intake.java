package org.usfirst.frc.team6418.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private VictorSP intakeRight = new VictorSP(0);
	private VictorSP intakeLeft = new VictorSP(1);
	private DoubleSolenoid intakeSolenoid = new DoubleSolenoid(2, 3);
	
    public Intake() {
    	intakeLeft.setInverted(true);
    }
    
    public void runWheels(double speed) {
    	intakeRight.set(speed);
    	intakeLeft.set(speed);
    }
    
    public void zucc() {
    	System.out.println("nice job team");
    }
    
    public void unHug() {
    	intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void hug() {
    	intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void stopWheels() {
    	runWheels(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

