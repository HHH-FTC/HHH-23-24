package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Chassis.StraferChassisBase;
import org.firstinspires.ftc.teamcode.Resources.RoadRunnerQuickstart.trajectorysequence.TrajectorySequence;

public class Square extends LinearOpMode {

    @Override
    public void runOpMode() {
        StraferChassisBase base = new StraferChassisBase(hardwareMap);

        waitForStart();

        TrajectorySequence seq = base.trajectorySequenceBuilder(base.getPoseEstimate())
                .forward(16)
//                .turn(Math.PI/2)
//                .forward(16)
//                .turn(Math.PI/2)
//                .forward(16)
//                .turn(Math.PI/2)
//                .forward(16)
//                .turn(Math.PI/2)
                .build();

        base.followTrajectorySequenceAsynfc(seq);
    }
}
