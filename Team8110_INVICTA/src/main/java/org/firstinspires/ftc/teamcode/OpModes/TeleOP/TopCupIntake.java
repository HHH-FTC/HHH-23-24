package org.firstinspires.ftc.teamcode.OpModes.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class TopCupIntake extends LinearOpMode {
    @Override
    public void runOpMode() {
        Servo lin = hardwareMap.get(Servo.class, "test");

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a)
                lin.setPosition(0);
            else if (gamepad1.b)
                lin.setPosition(0.5);
            else if (gamepad1.x)
                lin.setPosition(1);
        }
    }
}
