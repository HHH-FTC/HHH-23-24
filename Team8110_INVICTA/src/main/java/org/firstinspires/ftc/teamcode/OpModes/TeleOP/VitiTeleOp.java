package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Hardware.Robots.VitiTawiti;

@TeleOp
public class VitiTeleOp extends LinearOpMode {
    VitiTawiti robot;

    @Override
    public void runOpMode() {
        robot = new VitiTawiti(hardwareMap);

        robot.slides.motor.reset();

        waitForStart();

        while (opModeIsActive())  {
            robot.teleOpIntake(gamepad1);

            if (gamepad1.right_trigger > 0) {
                slideRun();
            } else if (gamepad1.left_trigger > 0) {
                robot.claw.runTo1();
            }

            if (gamepad1.right_bumper) {
                robot.slides.advance();
            } else if (gamepad1.left_bumper) {
                robot.slides.unadvance();
            }

            for (LynxModule l : robot.lynx) {
                telemetry.addData(l.isParent() + " Current", l.getCurrent(CurrentUnit.AMPS));
            }

            telemetry.addData("Slide Current", robot.slides.motor.dcMotorEx.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Slide Position", robot.slides.motor.getCurrentPosition());
            telemetry.addData("Outtake Position", robot.outtake.getCurrentPosition());
            telemetry.addData("Servo Positions (claw, push)", "(%f, %f)",
                    robot.claw.getPosition(), robot.push.getPosition());
            telemetry.addData("Positions:", robot.teleOpDrive(gamepad1));
            telemetry.addData("Slide Target", robot.slides.getTarget());
            telemetry.update();
        }
    }
    
    public void slideRun() {
        robot.slides.goToOne();
        robot.push.runTo2();
        robot.slides.goToZero();
        robot.claw.runTo2();
    }
}
