package org.firstinspires.ftc.teamcode.Lessons;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TestLinOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        TestRobot george = new TestRobot(hardwareMap, "frontLeft", "backLeft",
                "backRight", "frontRight", "claw");
        george.driveForward(1);
        sleep(10000);
        george.driveForward(0);

        george.setClawPos(0);

        george.setTarget(500);
    }

    // public static void main()

}
