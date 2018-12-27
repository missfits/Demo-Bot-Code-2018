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
import org.usfirst.frc.team6418.robot.commands.Teleop;

/**
 * Drivetrain subsystem
 */
public class DriveTrain extends Subsystem {
	final TalonSRX frontLeft = new TalonSRX(2);
	final TalonSRX rearLeft = new TalonSRX(3);
	final TalonSRX frontRight = new TalonSRX(1);
	final TalonSRX rearRight = new TalonSRX(4);
	private static ADXRS450_Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);

	/**
	 * Constructor; inverts left and right side of drivetrain
	 */
	public DriveTrain() {
		frontRight.setInverted(true);
		rearRight.setInverted(true);
	}

	/**
	 * Assigns drivetrain default command (when subsystem is idle) to teleop
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Teleop());
	}

	/**
	 * Drives robot forward/backward at specified speed
	 *
	 * @param speed desired speed of robot in % of motor power output
	 */
	public void drive(double speed) {
		tankDrive(speed, speed);
	}

	/**
	 * Brings the robot to a stop
	 */
	public void stopDrive() {
		drive(0);
	}

	/**
	 * Gets the position of encoders on the robot wheels
	 *
	 * @return pulse width position of encoders (double)
	 */
	public double getEncoderPosition() {
		 return rearLeft.getSensorCollection().getPulseWidthPosition();
	}

	/**
	 * Gets angle of the robot (0 being robot's starting orientation)
	 *
	 * @return angle of robot (double)
	 */
	public double getAngle() {
		return gyro.getAngle();
	}

	/**
	 * Resets the gyro sensor
	 */
	public void zeroGyro() {
		gyro.reset();
	}

	/**
	 * Rotates the robot with center of rotation in center of robot
	 *
	 * @param angle angle to turn robot at
	 */
	public void turn(double angle) {
		double speed = 0.3 * Math.signum(angle);
		frontLeft.set(ControlMode.PercentOutput, -speed);
		rearLeft.set(ControlMode.PercentOutput, -speed);
		rearRight.set(ControlMode.PercentOutput, speed);
		frontRight.set(ControlMode.PercentOutput, speed);
	}

	/**
	 * Strafes the robot at specified speed
	 *
	 * @param speed desired speed of robot in % of motor output
	 */
	public void strafe(double speed) {
		frontLeft.set(ControlMode.PercentOutput, speed);
		rearLeft.set(ControlMode.PercentOutput, -speed);
		rearRight.set(ControlMode.PercentOutput, speed);
		frontRight.set(ControlMode.PercentOutput, -speed);
	}

	/**
	 * Drives left and right side of drivetrain individually. Better for smoother and/or wider turns.
	 *
	 * @param lSpeed desired speed of left side of drivetrain in % of motor output
	 * @param rSpeed desired speed of right side of drivetrain in % of motor output
	 */
	public void tankDrive(double lSpeed, double rSpeed) {
		frontLeft.set(ControlMode.PercentOutput, lSpeed);
		rearLeft.set(ControlMode.PercentOutput, lSpeed);
		frontRight.set(ControlMode.PercentOutput, rSpeed);
		rearRight.set(ControlMode.PercentOutput, rSpeed);
	}
}