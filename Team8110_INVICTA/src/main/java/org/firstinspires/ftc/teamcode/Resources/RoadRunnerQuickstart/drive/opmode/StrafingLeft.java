package org.firstinspires.ftc.teamcode.Resources.RoadRunnerQuickstart.drive.opmode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Drivetrains.StraferChassisBase;

public class StrafingLeft {

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


    public void strafeLeft(int fl, int fr, double speed){
        flPose += fl;
        frPose += fr;

        frontLeft.setTargetPosition(flPose);
        frontRight.setTargetPosition(frPose);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(speed);
        frontRight.setPower(speed);

        //boolean b = frontLeft.isBusy() && frontRight.isBusy();
    }

}
