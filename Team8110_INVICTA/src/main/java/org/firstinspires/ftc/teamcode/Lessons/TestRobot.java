package org.firstinspires.ftc.teamcode.Lessons;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

public class TestRobot {
    List<DcMotor> wheels;
    Servo claw;
    CRServo wrist;

    public TestRobot(HardwareMap hwMap, String flName, String blName, String brName, String frName, String cName) {
        wheels.add(hwMap.get(DcMotor.class, flName));
        DcMotor frontRight = hwMap.get(DcMotorEx.class, frName);
        wheels.add(frontRight);
        wheels.add(hwMap.get(DcMotorEx.class, blName));
        wheels.add(hwMap.get(DcMotorEx.class, brName));

        wheels.get(3).setDirection(DcMotorSimple.Direction.REVERSE);
        wheels.get(1).setDirection(DcMotorSimple.Direction.REVERSE);

        claw = hwMap.get(Servo.class, cName);
    }

    public void driveForward(int power) {
        for (int i = 0; i < wheels.size(); i++) {
            wheels.get(i).setPower(power);
        }
        // -1 < power < 1

    }

    public void setTarget(int target) {
        for (DcMotor m : wheels) {
            m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            m.setTargetPosition(target);
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void setClawPos(int pos) {
        wrist.setPower(1);
        claw.setPosition(pos);
    }
}
