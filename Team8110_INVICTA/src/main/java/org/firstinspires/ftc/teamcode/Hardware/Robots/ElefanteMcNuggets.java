package org.firstinspires.ftc.teamcode.Hardware.Robots;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Motor;
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.ScissorLift;
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Webcam;
import org.openftc.easyopencv.OpenCvCamera;

import java.util.Arrays;
import java.util.List;

public class ElefanteMcNuggets {
    final HardwareMap hwmap;
    final ElefanteLift lift;
    final ConeClaw claw;
    final OpenCvCamera camera;
    final Motor left;
    final Motor right;
    private final Motor fRight;
    private final Motor fLeft;
    List<Motor> wheels;

    public ElefanteMcNuggets(HardwareMap hardwareMap) {
        this.hwmap = hardwareMap;
        this.lift = new ElefanteLift("lift");
        this.claw = new ConeClaw("claw");
        this.camera = new Webcam(hardwareMap).getCamera();
        this.left = new Motor("backLeft", hardwareMap);
        this.right = new Motor("backRight", hardwareMap);
        this.fRight = new Motor("frontRight", hardwareMap);
        this.fLeft = new Motor("frontLeft", hardwareMap);

        wheels = Arrays.asList(left, right);

        this.fLeft.setDirectionReverse();
    }

    public ConeClaw claw() {
        return claw;
    }

    public class ElefanteLift implements ScissorLift {
        private final Motor liftMotor;
        private final double initial;

        public ElefanteLift(String name) {
            liftMotor = new Motor(name, hwmap);
            liftMotor.reset();
            initial = liftMotor.getPosition();
        }

        @Override
        public void reverse() {
            switch (liftMotor.getDirection()) {
                case REVERSE:
                    liftMotor.setDirectionForward();
                    break;
                case FORWARD:
                    liftMotor.setDirectionReverse();
                    break;
            }
        }

        @Override
        public void power(double power) {
            liftMotor.setPower(power);
        }

        public void run(double position) {
//            telemetry.addData("started running to position", 0);
//            telemetry.update();
            liftMotor.runToPosition(position);
        }

        public Motor getMotor() {
            return liftMotor;
        }

        @Override
        public double getPosition() {
            return liftMotor.getCurrentPosition();
        }

        @Override
        public double getInches() {
            return liftMotor.getCurrPosInches();
        }

        @Override
        public void goToHigh() {
            liftMotor.runToPosition(3000, 1);
            liftMotor.reset();
        }

        public void goToGround() {
            liftMotor.runToPosition(3000, -1);
            liftMotor.reset();
        }

        @Override
        public void downLevel() {
            liftMotor.runToPosition((int) (getPosition() - 160));
        }
    }

    public class ConeClaw implements Claw {
        private Servo servo;

        public ConeClaw(String clawName) {
            servo = hwmap.get(Servo.class, clawName);
        }

        @Override
        public void open() {
            servo.setPosition(.8);
        }

        @Override
        public void close() {
            servo.setPosition(.86);
        }

        @Override
        public double getPosition() {
            return servo.getPosition();
        }
    }

    public ElefanteLift getLift() {
        return lift;
    }

    public ConeClaw getClaw() {
        return claw;
    }

    /*public void teleOpDrive(Gamepad gamepad) {
        switch (speed) {
            case NORMAL:
                if (gamepad.x) {
                    diff = 3.0;
                    speed = SLOW;
                }
                break;
            case SLOW:
                if (gamepad.x) {
                    diff = 2.0;
                    speed = NORMAL;
                }
                break;
        }

        this.setDrivePower(new Pose2d(
            gamepad.left_stick_y/diff,
            gamepad.right_stick_x/diff,
            gamepad.left_stick_x/diff
        ));
    }
*/
    public double teleOpDrive(Gamepad gamepad) {
        double drive = -gamepad.left_stick_y;
        double turn = gamepad.right_stick_x;

        left.setPower(drive);
        fLeft.setPower(drive);
        right.setPower(drive);
        fRight.setPower(drive);

        if(drive>0) {
            left.setPower(0);
            fLeft.setPower(0);
            right.setPower(.5);
            fRight.setPower(.4);
        }
        else if(drive<0) {
            left.setPower(-.5);
            fLeft.setPower(-.7);
            right.setPower(0);
            fRight.setPower(0);
        }

        if(turn>0) {
            left.setPower(-1);
            fLeft.setPower(-1);
            right.setPower(1);
            fRight.setPower(1);
        }
        else if(turn<0) {
            left.setPower(.7);
            fLeft.setPower(.7);
            right.setPower(.7);
            fRight.setPower(-.7);
        }



        return turn;
    }

    private static double point65(double x) {
        return Math.signum(x) * Math.min(.65, Math.abs(x));
    }

    public void teleOpClaw(Gamepad gamepad) {
        if (gamepad.a) {
            claw.open();
        } else if (gamepad.y) {
            claw.close();
        }
    }

    public void teleOpLift(Gamepad gamepad) {
        double down = (gamepad.left_bumper || gamepad.dpad_down) ? 0.75 : 0;
        double up = (gamepad.right_bumper || gamepad.dpad_up) ? 0.75 : 0;

        lift.power(down-up);
    }

    public void runToPosition(int position, double power) {

        for (Motor motor : wheels) {
            motor.runToPosition(position,power);
        }
    }

    public ElefanteLift lift() {
        return lift;
    }

    public void runToPosition(int position) {
        this.runToPosition(position*538,1);
    }


}
