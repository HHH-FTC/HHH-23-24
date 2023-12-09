package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Motor { //implements Mechanism {
    public DcMotorEx dcMotorEx;

    //Declare all the constants in the Motor class
    public double CPR;
    public double WHEEL_DIAMETER = 1;
    public double MAX_RPM;
    public double TICKS_PER_INCH;
    public double ARM_COUNTS_PER_DEGREE;
    public double NANOSECONDS_PER_MIN = 6e+10;
    public double GearRatio;

       /** Constructor for drive train motors
       @param name Pass in name of the motor on the RC phone config
       @param hwmap Pass in the hardwareMap from OpMode to initialize the motor */
    public Motor(String name, HardwareMap hwmap){
        dcMotorEx = hwmap.get(DcMotorEx.class, name);
    }

    /** Constructor for dead wheel encoders
       @param name Name of the motor connected to the respective encoder port
       @param cpr Encoder ticks per one revolution
       @param wheelDiameter Diameter of the dead wheel
       @param hwmap Pass in the hardwareMap from OpMode to initialize the motor
     */
    public Motor(String name , double cpr , double wheelDiameter, HardwareMap hwmap){
        dcMotorEx = hwmap.get(DcMotorEx.class, name);
        this.CPR = cpr;
        this.WHEEL_DIAMETER = wheelDiameter;
        this.TICKS_PER_INCH = cpr / (wheelDiameter * Math.PI);
        this.ARM_COUNTS_PER_DEGREE = (cpr / 360);
    }

    public Motor(String name , double cpr , double wheelDiameter, double GearRatio, HardwareMap hwmap){
        dcMotorEx = hwmap.get(DcMotorEx.class, name);
        this.CPR = cpr;
        this.WHEEL_DIAMETER = wheelDiameter;
        this.TICKS_PER_INCH = cpr / (wheelDiameter * Math.PI);
        this.ARM_COUNTS_PER_DEGREE = (cpr / 360);
        this.GearRatio = GearRatio;
    }

    public void reset(){
        dcMotorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dcMotorEx.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runToPosition(double target){
        dcMotorEx.setTargetPosition((int) target);
        dcMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        dcMotorEx.setPower(0.1);

        while (dcMotorEx.isBusy()){

        }

        reset();
    }

    public void runToPosition(int target, double power){
        dcMotorEx.setTargetPosition(target);
        dcMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        dcMotorEx.setPower(power);

        while (dcMotorEx.isBusy()){

        }

        reset();
    }

    // Testing
    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        dcMotorEx.setZeroPowerBehavior(zeroPowerBehavior);
    }
    public void setBreakMode(){
        dcMotorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setFloatMode(){
        dcMotorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients coefficients){
        dcMotorEx.setPIDFCoefficients(runMode, coefficients);
    }

    public double getCurrPosInches(){
        return dcMotorEx.getCurrentPosition() / TICKS_PER_INCH;
    }
    public double getCurrPosDegrees(){
        return GearRatio*(dcMotorEx.getCurrentPosition()/ARM_COUNTS_PER_DEGREE);//0.23809
    }

    public int getCurrentPosition(){
        return dcMotorEx.getCurrentPosition();
    }
    public double getPositionTicks(){
        return dcMotorEx.getCurrentPosition();
    }

    public double getVelocity(){
        return dcMotorEx.getVelocity();
    }

    public MotorConfigurationType getMotorType(){
        return dcMotorEx.getMotorType();
    }

    public void setMotorType(MotorConfigurationType motorConfigurationType){
        dcMotorEx.setMotorType(motorConfigurationType);
    }

    public void setDirectionForward(){
        dcMotorEx.setDirection(DcMotor.Direction.FORWARD);
    }
    public void setDirectionReverse(){
        dcMotorEx.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setPower(double power){
        dcMotorEx.setPower(power);
    }

    public void setVelocity(double ω){
        dcMotorEx.setVelocity(ω);
    }
    public void setVelocity(double ω, AngleUnit angleUnit){
        dcMotorEx.setVelocity(ω, angleUnit);
    }

    public void setMode(DcMotor.RunMode runMode) {
        dcMotorEx.setMode(runMode);
    }

    public double getPosition() {
        return getCurrentPosition();
    }

    public DcMotorSimple.Direction getDirection() {
        return dcMotorEx.getDirection();
    }
}
