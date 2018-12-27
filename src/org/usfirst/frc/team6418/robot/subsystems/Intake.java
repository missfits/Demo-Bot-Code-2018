package org.usfirst.frc.team6418.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intake subsystem
 */
public class Intake extends Subsystem {
	private VictorSP intakeRight = new VictorSP(0);
	private VictorSP intakeLeft = new VictorSP(1);
	private DoubleSolenoid intakeSolenoid = new DoubleSolenoid(2, 3);
	private DoubleSolenoid intakeTiltSolenoid = new DoubleSolenoid(4, 5);

    /**
     * Constructor; inverts left side of intake (so wheels will spin inwards/outwards)
     */
    public Intake() {
    	intakeLeft.setInverted(true);
    }

    /**
     * Can assign intake default command (when subsystem is idle) here
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Spins intake wheels at specified speed
     *
     * @param speed desired speed to spin wheels at in % output of motors
     */
    public void runWheels(double speed) {
    	intakeRight.set(speed);
    	intakeLeft.set(speed);
    }

    /**
     * Why
     */
    public void zucc() {
    	System.out.println("nice job team");
    }

    /**
     * Opens intake arms
     */
    public void unHug() {
    	intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * Closes intake arms
     */
    public void hug() {
    	intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * Stops running wheels
     */
    public void stopWheels() {
    	runWheels(0);
    }

    /**
     * Tilts intake upward
     */
    public void tiltUp() {
    	intakeTiltSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * Lets intake lie flat
     */
    public void tiltDown() {
    	intakeTiltSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}

