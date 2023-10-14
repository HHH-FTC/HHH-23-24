//package org.firstinspires.ftc.teamcode.Lessons;
//
//import ftc.rogue.blacksmith.BlackOp;
//import ftc.rogue.blacksmith.Scheduler;
//import ftc.rogue.blacksmith.listeners.ReforgedGamepad;
//
//public class TestBlackOp extends BlackOp {
//    @Override
//    public void go() {
//        ReforgedGamepad gm1 = getReforgedGamepad1();
//        TestRobot george = new TestRobot(hardwareMap, "frontLeft", "backLeft",
//                "backRight", "frontRight", "claw");
//
//        gm1.a.whileTrue(() -> {
//            george.driveForward(1);
//        });
//        gm1.a.whileFalse(() -> {
//            george.driveForward(0);
//        });
//
//        Scheduler.launchOnStart(this);
//    }
//}
