package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Hardware.Robots.VitiTawiti;

@TeleOp
public class VitiTeleOp extends LinearOpMode {
    VitiTawiti robot;

    @Override
    public void runOpMode() {
        robot = new VitiTawiti(hardwareMap);

        robot.slides.reset();

        waitForStart();

        robot.claw.runTo2();


        while (opModeIsActive())  {
            robot.teleOpIntake(gamepad1);

            if (gamepad1.right_trigger > 0) {
                robot.slides.setPower (1);
                sleep(1000);
                robot.slides.setPower(0);
                robot.outtake.setPower (1);
                sleep(250);
                robot.claw.runTo2();
                robot.outtake.setPower(0);

                sleep (500);
                robot.outtake.setPower(-1);
                sleep(250);
                robot.outtake.setPower(0);
                robot.slides.setPower(-1);
                sleep(1000);
                robot.slides.setPower(0);
            } else if (gamepad1.left_trigger > 0) {
                robot.claw.runTo1();
            }

            if (gamepad1.dpad_up) {
                robot.hang.setPower(1);
            } else if (gamepad1.dpad_down) {
                robot.hang.setPower(-1);
            } else {
                robot.hang.setPower(0);
            }

            for (LynxModule l : robot.lynx) {
                telemetry.addData(l.isParent() + " Current", l.getCurrent(CurrentUnit.AMPS));
            }

            telemetry.addData("Slide Current", robot.slides.dcMotorEx.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Slide Position", robot.slides.getCurrentPosition());
            telemetry.addData("Actuator Position", robot.hang.getCurrentPosition());
            telemetry.addData("Outtake Position", robot.outtake.getCurrentPosition());
            telemetry.addData("Claw Position", robot.claw.getPosition());
            telemetry.addData("Positions:", robot.teleOpDrive(gamepad1));
            telemetry.update();
        }
    }
}
