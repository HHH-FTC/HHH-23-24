package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Motor;

@TeleOp
public class Slides extends LinearOpMode {
    @Override
    public void runOpMode() {
        Motor slides = new Motor("slides", hardwareMap);
        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            slides.setPower(-gamepad1.left_stick_y);
            telemetry.addData("Slide Position", slides.getCurrPosInches());
            telemetry.update();
        }
    }
}
