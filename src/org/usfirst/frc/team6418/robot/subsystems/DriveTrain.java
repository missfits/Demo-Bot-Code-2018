/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6418.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
	final TalonSRX frontLeft = new TalonSRX(2);
	final TalonSRX rearLeft = new TalonSRX(3);
	final TalonSRX frontRight = new TalonSRX(1);
	final TalonSRX rearRight = new TalonSRX(4);
	private static ADXRS450_Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
	
	public DriveTrain(){
		frontRight.setInverted(true);
		rearRight.setInverted(true);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	public void drive(double speed) {
		frontLeft.set(ControlMode.PercentOutput, speed);
		rearLeft.set(ControlMode.PercentOutput, speed);
		frontRight.set(ControlMode.PercentOutput, speed);
		rearRight.set(ControlMode.PercentOutput, speed);
	}
	public void stopDrive() {
		drive(0);
	}
	public double getEncoderPosition() {
		 return rearLeft.getSensorCollection().getPulseWidthPosition();
	}
	public double getAngle() {
		return gyro.getAngle();
	}
	public void zeroGyro() {
		gyro.reset();
	}
	public void turn(double angle) {
		double speed = 0.3 * Math.signum(angle);
		frontLeft.set(ControlMode.PercentOutput, -speed);
		rearLeft.set(ControlMode.PercentOutput, -speed);
		rearRight.set(ControlMode.PercentOutput, speed);
		frontRight.set(ControlMode.PercentOutput, speed);
	}
}