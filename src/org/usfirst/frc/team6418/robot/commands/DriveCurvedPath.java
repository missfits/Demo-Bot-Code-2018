package org.usfirst.frc.team6418.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import usfirst.frc.team6418.robot.*;
import org.usfirst.frc.team6418.robot.Robot;

import java.awt.*;

public class DriveCurvedPath extends Command {
    private FalconPathPlanner pathPlanner;
    private int indexCount = 0;

    public DriveCurvedPath() {
        requires(Robot.driveTrain);

        double[][] scaleToSwitchCubePath = new double[][] {
                {0, 0},
                {0, 3},
                {3, 6},
                {3, 9}
        };

        double totalTime = 3; // seconds
        double timeStep = 0.02; // period of control loop on Rio, seconds
        double robotTrackWidth = 2; // distance between left and right wheels, feet

        pathPlanner = new FalconPathPlanner(scaleToSwitchCubePath);
        pathPlanner.calculate(totalTime, timeStep, robotTrackWidth);

        log("DriveCurvedPath ctor, path length: " + pathPlanner.smoothLeftVelocity.length);
    }

    private void log(String msg) {
        System.out.println(msg);
    }

    protected void execute() {
        // Converts velocity to percentage output
        double maxVelocity = 10.0; // feet per second
        double leftVelocity = pathPlanner.smoothLeftVelocity[indexCount][1];
        log("iteration " + indexCount + ", leftVelocity " + leftVelocity);
        double leftPower = leftVelocity / maxVelocity;
        log("          leftPower " + leftPower);
        double rightVelocity = pathPlanner.smoothRightVelocity[indexCount][1];
        log("          rightVelocity " + rightVelocity);
        double rightPower = rightVelocity / maxVelocity;
        log("          rightPower " + rightPower);
        // motors are still inverted for some reason, have to pass power * -1 to go forward
        if (GraphicsEnvironment.isHeadless()) {
            Robot.driveTrain.tankDrive(leftPower, rightPower);
        }
        indexCount++;
    }

    protected boolean isFinished() {
        return indexCount >= pathPlanner.smoothLeftVelocity.length;
    }
}
