package org.firstinspires.ftc.teamcode.Hardware.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class Webcam {
    private OpenCvCamera camera;

    public Webcam(HardwareMap hardwareMap, String webcamName) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcam = hardwareMap.get(WebcamName.class, webcamName);

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcam, cameraMonitorViewId);
    }

    public Webcam(HardwareMap hardwareMap) {
        this(hardwareMap, "webcam");
    }

    public OpenCvCamera getCamera() {
        return camera;
    }

}
