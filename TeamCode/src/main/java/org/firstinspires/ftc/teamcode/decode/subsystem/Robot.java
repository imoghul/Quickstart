package org.firstinspires.ftc.teamcode.decode.subsystem;


import static org.firstinspires.ftc.teamcode.decode.subsystem.Common.mTelemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.decode.util.ActionScheduler;
import org.firstinspires.ftc.teamcode.decode.util.BulkReader;
import org.firstinspires.ftc.teamcode.decode.util.LoopUtil;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Config
public final class Robot {
    public final Follower drivetrain;
    public final BulkReader bulkReader;
    public final TouchSensor magnet;
    public final ActionScheduler actionScheduler;
//    public final AutoAligner autoAligner;
//    public final LimelightEx limelightEx;

    public enum State {
        NEUTRAL,
        EXTENDO_OUT,
        SETUP_SPECIMEN,
        TO_BE_TRANSFERRED,
        TRANSFERRED,
        SETUP_SCORE_BASKET,
        SETUP_HANG,
        DO_HANG,
        SCORED_BASKET,
        SETUP_DROP_SAMPLE,
        WALL_PICKUP
    }

    State currentState = State.NEUTRAL;

    /**
     * Constructor used in teleOp classes that makes the current pose2d, 0
     * @param hardwareMap A constant map that holds all the parts for config in code
     */
    public Robot(HardwareMap hardwareMap) {
        this(hardwareMap, false);
    }

    /**
     * Constructor for instantiating a new 'robot' class
     * @param hardwareMap: A constant map that holds all the parts for config in code
     */
    public Robot(HardwareMap hardwareMap, boolean isAuto) {
//        Limelight3A limelight3A = hardwareMap.get(Limelight3A.class, "limelight");

        drivetrain = Constants.createFollower(hardwareMap);
        bulkReader = new BulkReader(hardwareMap);
//        autoAligner = new AutoAligner(hardwareMap);
//        limelightEx = new LimelightEx(limelight3A, hardwareMap);
//        autoWallPickUp = new AutoWallPickUp(limelightEx);
        actionScheduler = new ActionScheduler();
        magnet = hardwareMap.get(TouchSensor.class, "magnet");

//        limelight3A.stop();
//        limelight3A.start();
    }

    // Reads all the necessary sensors (including battery volt.) in one bulk read
    public void readSensors() {
        bulkReader.bulkRead();
    }

    // Runs all the necessary mechanisms
    public void run() {

        actionScheduler.run();
        update();
    }

    public void update(){
    }

    // Prints data on the driver hub for debugging and other uses
    public void printTelemetry() {
        mTelemetry.addData("Robot State", currentState.name());
        mTelemetry.addData("Loop time (hertz)", LoopUtil.getLoopTimeInHertz());

        mTelemetry.addData("current x", drivetrain.poseTracker.getPose().getX());
        mTelemetry.addData("current y", drivetrain.poseTracker.getPose().getY());
        mTelemetry.addData("current Heading", drivetrain.poseTracker.getPose().getHeading());

//        arm.printTelemetry();
//        intake.printTelemetry();
//        autoAligner.printTelemetry();
//        autoWallPickUp.printTelemetry();
        mTelemetry.update();
    }

    public State getCurrentState() {
        return currentState;
    }
    public void setCurrentState(State state) {
        this.currentState = state;
    }
}
