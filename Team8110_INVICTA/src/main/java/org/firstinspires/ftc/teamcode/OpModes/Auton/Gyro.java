package org.firstinspires.ftc.teamcode.OpModes.Auton;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous
public class Gyro extends LinearOpMode {

    Pid robot = new Pid();
    private ElapsedTime time = new ElapsedTime();

    private Orientation lastAngles = new Orientation();
    private BNO055IMU imu;
    private double currAngle = 0.0;
    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        waitForStart();

        turn(90);

        sleep(3000);

        turnTo(-90);
    }

    //reset method
    public void resetAngle(){
        lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XZY, AngleUnit.DEGREES);
         currAngle = 0;
    }

    //getAngle
    public double getAngle(){

        Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XZY, AngleUnit.DEGREES);

        double delatAngle = orientation.firstAngle - lastAngles.firstAngle;

        if(delatAngle > 180){
            delatAngle -= 360;
        } else if (delatAngle <= -180) {
            delatAngle += 360;
        }

        currAngle += delatAngle;
        lastAngles = orientation;
        telemetry.addData("gyro", orientation.firstAngle);
        return currAngle;
    }

    public void turn(double degress){

        resetAngle();

        double error = degress;

        while (opModeIsActive() && Math.abs(error) > 2){
            double motorPower = (error < 0 ? -0.3 : 0.3);
            //rigth wheels (positive)
            //left wheel (negative)
            robot.setMotorPower(-motorPower, motorPower, -motorPower, motorPower);
            error = degress - getAngle();
            //how far from current Angle
            telemetry.addData("error", error);
        }

        robot.setPower(0);
    }

    public void turnTo(double degrees){
        Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XZY, AngleUnit.DEGREES);

        double error = degrees - orientation.firstAngle;

        if(error > 180){
             error -= 360;
        } else if (error < -180) {
            error += 360;
        }

        turn(error);

    }
}
