package org.firstinspires.ftc.teamcode.Hardware.Mechanisms;

import com.qualcomm.robotcore.hardware.Servo;

public interface Claw {
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
