package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Hardware.Robots.VitiTawiti;

@TeleOp
public class VitiTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        VitiTawiti base = new VitiTawiti(hardwareMap);

        base.slides.reset();

        waitForStart();

        while (opModeIsActive()) {
            base.teleOpSlides(gamepad1);
            base.teleOpClaw(gamepad1);
            base.teleOpIntake(gamepad1);

            for (LynxModule l : base.lynx) {
                telemetry.addData(l.isParent() + " Current", l.getCurrent(CurrentUnit.AMPS));
            }
            telemetry.addData("Slide Current", base.slides.dcMotorEx.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Slide Position", base.slides.getCurrentPosition());
            telemetry.addData("Positions:", base.teleOpDrive(gamepad1));
            telemetry.update();
        }
    }
}
