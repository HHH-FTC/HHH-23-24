package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Robots.ElefanteMcNuggets;

@TeleOp
public class ElefanteTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        ElefanteMcNuggets robot = new ElefanteMcNuggets(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.addData("Lift Position", robot.lift().getPosition());
            telemetry.addData("Lift Height", robot.lift().getInches());
            telemetry.addData("Claw Position", robot.claw().getPosition());
            telemetry.update();

            robot.teleOpLift(gamepad1);
            robot.teleOpClaw(gamepad1);
            robot.teleOpDrive(gamepad1);
        }

    }
}
