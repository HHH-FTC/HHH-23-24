package org.firstinspires.ftc.teamcode.Hardware.Robots;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Hardware.Drivetrains.StraferChassisBase;
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Motor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

public class VitiTawiti extends StraferChassisBase {
    final public Motor intake, outtake;
    final public LinearSlide slides;
    public final VitiClaw claw;
    public final VitiClaw push;
    final public List<LynxModule> lynx;

    final public VisionPortal camera;
    final public TfodProcessor tf;
    final public AprilTagProcessor atag;

    public VitiTawiti(HardwareMap hwMap) {
        super(hwMap);

        Motor slideM = new Motor("slides", hwMap);
        slideM.setDirectionReverse();
        slideM.setZeroPowerBehavior(BRAKE);
        slides = new LinearSlide(slideM, 0, 500, 250);

        outtake = new Motor("outtake", hwMap);
        outtake.setDirectionReverse();

        intake = new Motor("intake", hwMap);
        intake.setDirectionReverse();

        lynx = hwMap.getAll(LynxModule.class);

        claw = new VitiClaw(hwMap, 0, .75);
        push = new VitiClaw(hwMap, 0, .75);

        tf = TfodProcessor.easyCreateWithDefaults();
        atag = AprilTagProcessor.easyCreateWithDefaults();
        camera = VisionPortal.easyCreateWithDefaults(hwMap.get(WebcamName.class, "Webcam 1"), tf, atag);
    }

    public static class VitiClaw {
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

        public double getPosition() {
            return claw.getPosition();
        }
    }

    public static class LinearSlide {
        public final Motor motor;
        public double zero, target, increment;
        public int advance;

        public LinearSlide(Motor motor, double zero, double target, double increment) {
            this.motor = motor;
            this.zero = zero;
            this.target = target;
            this.increment = increment;
            this.advance = 0;
        }

        public void goToZero() {
            motor.runToPosition(zero);
        }

        public void goToOne() {
            motor.runToPosition(target + (increment * advance));
        }

        public void advance() {
            this.advance += 1;
        }

        public double getTarget() {
            return target + (increment * advance);
        }

        public void unadvance() {
            this.advance -= 1;
        }
    }

    public Pose2d teleOpDrive(Gamepad gamepad) {
        Pose2d loc = new Pose2d(-gamepad.left_stick_y, -gamepad.right_stick_x, -gamepad.left_stick_x);
        this.setDrivePower(loc);
        return loc;
    }

    public void teleOpIntake(Gamepad gp) {
        if (gp.a) {
            intake.setPower(0.5);
        } else if (gp.b) {
            intake.setPower(0);
        }
    }

}
