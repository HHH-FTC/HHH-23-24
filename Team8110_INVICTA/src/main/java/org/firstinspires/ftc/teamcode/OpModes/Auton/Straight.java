package org.firstinspires.ftc.teamcode.OpModes.Auton;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrains.StraferChassisBase;

@Autonomous(name="PID Straight")
public class Straight extends LinearOpMode {

    StraferChassisBase base;
    HardwareMap hwm;


    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;


    public int flPose;
    public int frPose;
    public int blPose;
    public int brPose;
    public BNO055IMU imu;

    ElapsedTime timer = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        flPose = 0;
        frPose = 0;

        waitForStart();
        //toStriaght(1000,1000,0.25);
        move(1000,1000,0.25);
    }

    public void move(int fl, int fr, double speed){
        flPose += fl;
        frPose += fr;

        frontLeft.setTargetPosition(flPose);
        frontRight.setTargetPosition(frPose);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(speed);
        frontRight.setPower(speed);

        //boolean b = frontLeft.isBusy() && frontRight.isBusy();

        while(opModeIsActive()){
            idle();
        }
    }

    public void toTurn(int f, double speed){
        flPose += f;
        frPose += -f;
        blPose += f;
        brPose += -f;

        frontLeft.setTargetPosition(flPose);
        frontRight.setTargetPosition(frPose);
        backLeft.setTargetPosition(blPose);
        backRight.setTargetPosition(brPose);


        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);

        //boolean b = frontLeft.isBusy() && frontRight.isBusy();

        while(opModeIsActive()){
            idle();
        }
    }


    //front Left and backRight move sytrighjt

    public void toStriaght(int fl, int br, double speed){
        flPose += fl;
        brPose += br;

        frontLeft.setTargetPosition(flPose);
        backRight.setTargetPosition(brPose);

        frontLeft.setPower(speed);
        backRight.setPower(speed);

        //boolean b = frontLeft.isBusy() && frontRight.isBusy();

        while(opModeIsActive()){
            idle();
        }
    }
}