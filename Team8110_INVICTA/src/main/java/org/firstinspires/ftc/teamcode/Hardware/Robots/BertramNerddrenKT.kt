package org.firstinspires.ftc.teamcode.Hardware.Robots

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.Hardware.Drivetrains.StraferChassisBase
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Motor

class BertramNerddrenKT(hardwareMap: HardwareMap) : StraferChassisBase(hardwareMap) {
    private val arm: Motor = Motor("arm", hardwareMap)

    fun teleOpDrive(gamepad: Gamepad): Pose2d {
        val loc = Pose2d(gamepad.left_stick_y.toDouble(), -gamepad.right_stick_x.toDouble(), -gamepad.left_stick_x.toDouble())
        setDrivePower(loc)
        return loc
    }

    fun goCm(cm: Int) {
        val go: Trajectory = trajectoryBuilder(poseEstimate).forward(cm.toDouble()).build()
        followTrajectory(go)
    }

    fun teleOpArm(gp: Gamepad) {
        when {
            !gp.dpad_up && !gp.dpad_down -> arm.setPower(0.1)
            gp.dpad_up -> arm.setPower(1.0)
            gp.dpad_down -> arm.setPower(-1.0)
        }
    }
}

