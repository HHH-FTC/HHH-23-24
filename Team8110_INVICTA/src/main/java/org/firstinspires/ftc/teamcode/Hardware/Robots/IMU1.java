package org.firstinspires.ftc.teamcode.Hardware.Robots;

import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.Hardware.Mechanisms.InertialMeasurementUnit;

public class IMU1 extends InertialMeasurementUnit {

    public IMU1(HardwareMap hardwareMap) {
        super(XYZ, hardwareMap);
    }
}
