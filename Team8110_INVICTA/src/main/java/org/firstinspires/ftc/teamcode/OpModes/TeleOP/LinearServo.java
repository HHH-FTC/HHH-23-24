package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class LinearServo extends LinearOpMode {

    @Override
    public void runOpMode() {
        Servo lins = hardwareMap.get(Servo.class, "slow");
        lins.setPosition(0);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a)
                lins.setPosition(0);
            else if (gamepad1.b)
                lins.setPosition(0.5);
            else if (gamepad1.x)
                lins.setPosition(1);

            telemetry.addData("Gamepad", gamepad1);
            telemetry.update();

            gamepad1.reset();
        }
    }
}
