package org.firstinspires.ftc.teamcode.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Chassis.StraferChassisBase;

@Autonomous
public class Square extends LinearOpMode {

    @Override
    public void runOpMode() {
        StraferChassisBase base;
        base = new StraferChassisBase(hardwareMap);

        Pose2d initPose = base.getPoseEstimate();

        telemetry.addData("Start Pose", initPose);
        telemetry.update();

        //trajectory
        Trajectory mySquare = base.trajectoryBuilder(initPose)
                .forward(20)
                .build();

        waitForStart();
        if(isStopRequested())return;

        base.followTrajectory(mySquare);
    }

}