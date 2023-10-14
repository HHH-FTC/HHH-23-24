package org.firstinspires.ftc.teamcode.OpModes.Auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Chassis.StraferChassisBase;

@Autonomous
public class Square extends LinearOpMode {

    @Override
    public void runOpMode() {
        StraferChassisBase base;
        base = new StraferChassisBase(hardwareMap);

        //trajectory
        Trajectory mySquare = base.trajectoryBuilder(new Pose2d())
                .forward(20)
                .build();

        waitForStart();
        if(isStopRequested())return;

        base.followTrajectory(mySquare);
    }

}