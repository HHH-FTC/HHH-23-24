package org.firstinspires.ftc.teamcode.Hardware;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.Chassis.StraferChassisBase;

public class BertramNerddren extends StraferChassisBase {
    public BertramNerddren(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    public Pose2d teleOpDrive(Gamepad gamepad) {
        Pose2d loc = new Pose2d(gamepad.left_stick_y, -gamepad.right_stick_x, -gamepad.left_stick_x);
        this.setDrivePower(loc);
        return loc;
    }
}
