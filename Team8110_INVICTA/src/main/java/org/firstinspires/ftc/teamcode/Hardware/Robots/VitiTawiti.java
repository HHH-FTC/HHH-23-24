package org.firstinspires.ftc.teamcode.Hardware.Robots;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrains.StraferChassisBase;
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Motor;

import java.util.List;

public class VitiTawiti extends StraferChassisBase {
    public Motor slides;
    Motor intake;
    boolean oldX, oldA;
    VitiClaw claw;
    public List<LynxModule> lynx;

    public VitiTawiti(HardwareMap hwMap) {
        super(hwMap);
        slides = new Motor("slides", hwMap);
        slides.setDirectionReverse();
        slides.setZeroPowerBehavior(BRAKE);
        intake = new Motor("intake", hwMap);
        intake.setDirectionReverse();
        lynx = hwMap.getAll(LynxModule.class);
        claw = new VitiClaw(hwMap, 0, .75);
    }

    public class VitiClaw {
        Servo claw;
        double pos1, pos2;

        public VitiClaw(HardwareMap hwMap, double pos1, double pos2) {
            claw = hwMap.get(Servo.class, "claw");
            this.pos1 = pos1;
            this.pos2 = pos2;
        }

        public void runTo1() {
            claw.setPosition(pos1);
        }

        public void runTo2() {
            claw.setPosition(pos2);
        }
    }

    public Pose2d teleOpDrive(Gamepad gamepad) {
        Pose2d loc = new Pose2d(-gamepad.left_stick_y, -gamepad.right_stick_x, -gamepad.left_stick_x);
        this.setDrivePower(loc);
        return loc;
    }

    public void goCm(int cm) {
        Trajectory go = this.trajectoryBuilder(this.getPoseEstimate()).forward(cm).build();
        this.followTrajectory(go);
    }

    public void teleOpSlides(Gamepad gp) {
        if (!gp.dpad_up && !gp.dpad_down) {
            slides.setPower(0);
        } else if (gp.dpad_up) {
            slides.setPower(1);
        } else if (gp.dpad_down) {
            slides.setPower(-1);
        }
    }

    public void teleOpClaw(Gamepad gp) {
        if (gp.x) {
            claw.runTo2();
        } else if (gp.y) {
            claw.runTo1();
        }
    }
    public void teleOpIntake(Gamepad gp) {
        if (gp.a && !oldA) {
            intake.setPower(0.5);
            oldA = !oldA;
        } else if (!gp.a && oldA) {
            intake.setPower(0);
            oldA = !oldA;
        }
    }
}
