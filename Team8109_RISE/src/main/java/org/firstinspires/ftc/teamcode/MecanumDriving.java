package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MecanumDriving extends LinearOpMode {

    @Override
    public void runOpMode() {
        StraferChassisBase base = new StraferChassisBase(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            Pose2d pos = new Pose2d(-gamepad1.left_stick_y, -gamepad1.right_stick_x, -gamepad1.left_stick_x);
            base.setWeightedDrivePower(pos);
            telemetry.addData("Positions:", pos);
            telemetry.update();
        }
    }
}
