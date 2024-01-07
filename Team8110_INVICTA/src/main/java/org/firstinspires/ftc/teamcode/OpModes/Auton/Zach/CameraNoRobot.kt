package org.firstinspires.ftc.teamcode.OpModes.Auton.Zach

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector
import org.firstinspires.ftc.vision.VisionPortal
import org.firstinspires.ftc.vision.tfod.TfodProcessor

class CameraNoRobot : LinearOpMode() {

    override fun runOpMode() {
        val tf = TfodProcessor.easyCreateWithDefaults()
        val camera = VisionPortal.easyCreateWithDefaults(
            hardwareMap.get(WebcamName::class.java, "Webcam 1"),
            tf
        )

        waitForStart()

        camera.resumeStreaming()
        while(opModeIsActive()) {
            telemetryTfod(tf)
        }
    }

    private fun telemetryTfod(tf: TfodProcessor) {
        val currentRecognitions = tf.recognitions
        telemetry.addData("# Objects Detected", currentRecognitions.size)

        // Step through the list of recognitions and display info for each one.
        for (recognition in currentRecognitions) {
            val x = ((recognition.left + recognition.right) / 2).toDouble()
            val y = ((recognition.top + recognition.bottom) / 2).toDouble()
            telemetry.addData("", " ")
            telemetry.addData(
                "Image",
                "%s (%.0f %% Conf.)",
                recognition.label,
                recognition.confidence * 100
            )
            telemetry.addData("- Position", "%.0f / %.0f", x, y)
            telemetry.addData("- Size", "%.0f x %.0f", recognition.width, recognition.height)
        } // end for() loop
    } // end method telemetryTfod()
}