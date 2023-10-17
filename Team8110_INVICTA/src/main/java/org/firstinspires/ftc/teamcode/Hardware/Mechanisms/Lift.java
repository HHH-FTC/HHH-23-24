package org.firstinspires.ftc.teamcode.Hardware.Mechanisms;

public interface Lift  {


    /**
     * Alters the power of the motor
     * @param power Power to set the motor to
     */
    public void power(double power);

    /**
     * Gets the position of the motor
     * @return the motor position
     */
    public double getPosition();

    /**
     * Gets the position the scissor lift is at in inches
     * @return the position in inches
     */
    public  double getInches();

    /**
     * Moves the scissor lift to the next position
     */
    public void goToHigh();

    /**
     * Moves the scissor lift to the previous position
     */
    public void downLevel();

    public void reverse();
}

