package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Chassis.StraferChassisBase;

import ftc.rogue.blacksmith.BlackOp;
import ftc.rogue.blacksmith.Scheduler;
import ftc.rogue.blacksmith.annotations.CreateOnGo;
import ftc.rogue.blacksmith.listeners.ReforgedGamepad;

@TeleOp(name="SpeedControls")
public class TestBaseBot extends BlackOp {
    @CreateOnGo(passHwMap = true)
    StraferChassisBase base;
    @Override
    public void go() {
        ReforgedGamepad gp = getReforgedGamepad1();
        MultipleTelemetry tm = mTelemetry();
        Servo slow = hwMap().get(Servo.class, "slow");
        Servo fast = hwMap().get(Servo.class, "fast");

        Pose2d loc = new Pose2d(gp.left_stick_y.get(), -gp.right_stick_x.get(), -gp.left_stick_x.get());
        Pose2d halfloc = new Pose2d(loc.getX()/2, loc.getY()/2, loc.getY()/2);

        String mode = gp.right_bumper.get() ? "Fast" : "Slow";
        gp.right_bumper.whileTrue(() -> base.setWeightedDrivePower(loc));
        gp.right_bumper.whileFalse(() -> base.setWeightedDrivePower(halfloc));

        gp.a.onJustTrue(() -> {
            slow.setPosition(0);
            fast.setPosition(0);
        });
        gp.b.onJustTrue(() -> {
            slow.setPosition(0.5);
            fast.setPosition(0.5);
        });
        gp.x.onJustTrue(() -> {
            slow.setPosition(1);
            fast.setPosition(1);
        });

        Scheduler.launchOnStart(this, () -> {
            tm.addData("Mode", mode);
            tm.addData("Fast Pos", loc);
            tm.addData("Slow Pos", loc);
        });
    }
}
