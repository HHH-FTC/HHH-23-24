package org.firstinspires.ftc.teamcode.Resources;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

abstract public class RobotCommon extends LinearOpMode {
    /**
     * Roadrunner wrapped object - only change the units here if something else is being used
     */
    protected RoadrunnerWrapper pathing;

    /**
     * Name object for the webcam
     */
    private WebcamName webcameraName;

    /**
     * The actual camera object to apply a pipeline to
     */
    private OpenCvCamera camera;

    /**
     * Pipeline object
     */
    // Add pipeline - pipeline should be private (only used here)
    private OpenCvPipeline pipeline = new OpenCvPipeline() {
        @Override
        public Mat processFrame(Mat input) {
            return null;
        }
    };


    /**
     * Method to execute the init phase. Add vision code here
     */
    protected void initialize() {
        initHardware();
        initCamera();
        pathing.build();

        // Add your custom initialization loop here
        do {
            telemetry.addData("Initialized.", "");
        }
        while (!opModeIsActive());
    }

    /**
     * Method to execute after init phase. Should probably not need to be changed
     */
    protected void run() {
        pathing.follow();

        // Add any other runtime code here - you probably won't though
    }


    /**
     * Method to init non-drivetrain hardware before entering the init phase.
     */
    private void initHardware() {
        // Add your hardware initialization here (arm motor, servos, etc.)

    }

    /**
     * Initialize the camera and set its properties.
     * Postcondition: camera has been properly assigned
     */
    private void initCamera() {
        // Build the camera objects - most likely you won't need to change this, but if you've renamed your webcam then you will!
//        WebcamName name = hardwareMap.get(WebcamName.class, "Webcam 1");
        webcameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());


        // Create a new camera object in openCV
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcameraName, cameraMonitorViewId);

        camera.setPipeline(pipeline);

        // Start the camera stream or throws an error
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                telemetry.update();
                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                throw new RuntimeException("Error in camera initialization! Error code " + errorCode);
            }
        });
    }

    /**
     * Method to set the starting position of the robot.
     * Moved to the end because this method should NOT be changed.
     *
     * @param x       the starting x position in the units specified
     * @param y       the starting y position in the units specified
     * @param heading the starting heading - will convert to radians
     *                <strong>LATER, IN RoadrunnerWrapper!!!!</strong>
     */
    protected void setStartPose(double x, double y, double heading) {
        pathing.setStartPose(x, y, heading);
    }
}
