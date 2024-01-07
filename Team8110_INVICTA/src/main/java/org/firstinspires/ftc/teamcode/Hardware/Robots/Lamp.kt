package org.firstinspires.ftc.teamcode.Hardware.Robots

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.Hardware.Drivetrains.StraferChassisBase
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.Motor
import org.firstinspires.ftc.vision.VisionPortal
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor
import org.firstinspires.ftc.vision.tfod.TfodProcessor

class Lamp(hwMap: HardwareMap) : StraferChassisBase(hwMap) {
    val intake: Motor
    val outtake: Motor
    val slides: LinearSlide
    val claw: VitiClaw
    val push: VitiClaw
    val lynx: List<LynxModule>
    val camera: VisionPortal
    val tf: TfodProcessor
    val atag: AprilTagProcessor

    init {
        val slideM = Motor("slides", hwMap)
        slideM.setDirectionReverse()
        slideM.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE)
        slides = LinearSlide(slideM, 0.0, 500.0, 250.0)
        outtake = Motor("outtake", hwMap)
        outtake.setDirectionReverse()
        intake = Motor("intake", hwMap)
        intake.setDirectionReverse()
        lynx = hwMap.getAll(LynxModule::class.java)
        claw = VitiClaw(hwMap, 0.0, .75)
        push = VitiClaw(hwMap, 0.0, .75)
        tf = TfodProcessor.easyCreateWithDefaults()
        atag = AprilTagProcessor.easyCreateWithDefaults()
        camera = VisionPortal.easyCreateWithDefaults(
            hwMap.get(WebcamName::class.java, "Webcam 1"),
            tf,
            atag
        )
    }

    class VitiClaw(hwMap: HardwareMap, var pos1: Double, var pos2: Double) {
        var claw: Servo

        init {
            claw = hwMap.get(Servo::class.java, "claw")
        }

        fun runTo1() {
            claw.position = pos1
        }

        fun runTo2() {
            claw.position = pos2
        }

        val position: Double
            get() = claw.position
    }

    class LinearSlide(
        val motor: Motor,
        var zero: Double,
        var target: Double,
        var increment: Double
    ) {
        var advance = 0
        fun goToZero() {
            motor.runToPosition(zero)
        }

        fun goToOne() {
            motor.runToPosition(target + increment * advance)
        }

        fun advance() {
            advance += 1
        }

        fun getTarget(): Double {
            return target + increment * advance
        }

        fun unadvance() {
            advance -= 1
        }
    }

    fun teleOpDrive(gamepad: Gamepad): Pose2d {
        val loc = Pose2d(
            -gamepad.left_stick_y.toDouble(),
            -gamepad.right_stick_x.toDouble(),
            -gamepad.left_stick_x.toDouble()
        )
        setDrivePower(loc)
        return loc
    }

    fun teleOpIntake(gp: Gamepad) {
        if (gp.a) {
            intake.setPower(0.5)
        } else if (gp.b) {
            intake.setPower(0.0)
        }
    }
}