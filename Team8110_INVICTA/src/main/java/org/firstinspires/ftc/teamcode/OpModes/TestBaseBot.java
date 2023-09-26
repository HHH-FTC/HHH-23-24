package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Chassis.StraferChassisBase;

@TeleOp
public class TestBaseBot extends LinearOpMode {

    @Override
    public void runOpMode() {
        StraferChassisBase base = new StraferChassisBase(hardwareMap);

        while (opModeIsActive()) {
            Pose2d loc = new Pose2d(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            base.setDrivePower(loc);

            telemetry.addData("Positions:", loc);
            telemetry.update();
        }
    }
}
