package org.firstinspires.ftc.team8110_Invicta.Hardware.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public interface Claw extends Mechanism {
    Servo claw = null;
    Position position = Position.CLOSED;

    public void open();

    public void close();

    public default Position getState() {
        return position;
    }

    public enum Position {
        OPEN,
        CLOSED,
        OTHER
    }

    public double getPosition();

}
