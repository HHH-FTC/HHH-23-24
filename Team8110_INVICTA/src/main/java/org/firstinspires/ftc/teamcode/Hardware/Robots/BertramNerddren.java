package org.firstinspires.ftc.teamcode.Hardware.Robots;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrains.StraferChassisBase;
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Motor;

public class BertramNerddren extends StraferChassisBase {

    public BertramNerddren(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public Pose2d teleOpDrive(Gamepad gamepad) {
        Pose2d loc = new Pose2d(gamepad.left_stick_y, -gamepad.right_stick_x, -gamepad.left_stick_x);
        this.setDrivePower(loc);
        return loc;
    }

    public void goCm(double cm) {
        Trajectory go = this.trajectoryBuilder(this.getPoseEstimate()).forward(cm).build();
        this.followTrajectory(go);
    }
}
