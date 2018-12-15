/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6418.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team6418.robot.commands.TiltIntake.TiltPosition;
import org.usfirst.frc.team6418.robot.commands.IntakeHug.HugPosition;
import org.usfirst.frc.team6418.robot.commands.*;

enum XBoxButtons {
	DONOTUSE, A, B, X, Y, LEFT_BUMPER, RIGHT_BUMPER, BACK, START
}

enum XBoxAxes {
	DONOTUSE, LEFT_Y, LEFT_TRIGGER, RIGHT_TRIGGER, DONOTUSE2, RIGHT_Y
}
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	Joystick xBox = new Joystick(0);
	
	public Button aButton = new JoystickButton(xBox,1);
	public Button bButton = new JoystickButton(xBox,2);
	public Button xButton = new JoystickButton(xBox,3);
	public Button yButton = new JoystickButton(xBox,4);
	public Button leftBumperButton = new JoystickButton(xBox,5);
	public Button rightBumperButton = new JoystickButton(xBox,6);
	public Button backButton = new JoystickButton(xBox,7);
	public Button startButton = new JoystickButton(xBox,8);

	public double leftStickY() {return xBox.getRawAxis(1);}
	public double leftStickX() {return xBox.getRawAxis(0);}
	public double rightStickY() {return xBox.getRawAxis(5);}
	public double rightStickX() {return xBox.getRawAxis(4);}
	public boolean leftTriggerPressed() {return xBox.getRawAxis(2) > 0.2;}
	public boolean rightTriggerPressed() {return xBox.getRawAxis(3) > 0.2;}
	
	public OI() {
		yButton.whenPressed(new TiltIntake(TiltPosition.UP));
		aButton.whenPressed(new TiltIntake(TiltPosition.DOWN));
		leftBumperButton.whenPressed(new IntakeHug(HugPosition.HUG));
		rightBumperButton.whenPressed(new IntakeHug(HugPosition.UNHUG));
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	}
}
