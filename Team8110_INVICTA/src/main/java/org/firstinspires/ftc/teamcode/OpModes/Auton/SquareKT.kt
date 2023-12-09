package org.firstinspires.ftc.teamcode.OpModes.Auton

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.Hardware.Drivetrains.StraferChassisBase

@Autonomous
class SquareKT : LinearOpMode() {
    override fun runOpMode() {
        var base: StraferChassisBase
        base = StraferChassisBase(hardwareMap)

        val initPose: Pose2d = base.poseEstimate

        telemetry.addData("Start Pose", initPose)
        telemetry.update()

        val mySquare: Trajectory = base.trajectoryBuilder(initPose)
            .forward(20.0)
            .build()

        waitForStart()

        if (isStopRequested) return
        base.followTrajectory(mySquare)
    }
}

