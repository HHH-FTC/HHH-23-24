package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.BertramNerddren;
import org.firstinspires.ftc.teamcode.Hardware.Chassis.StraferChassisBase;

@TeleOp
public class LinTestBaseBot extends LinearOpMode {

    @Override
    public void runOpMode() {
        BertramNerddren base = new BertramNerddren(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Positions:", base.teleOpDrive(gamepad1));
            telemetry.update();
        }
    }
}
