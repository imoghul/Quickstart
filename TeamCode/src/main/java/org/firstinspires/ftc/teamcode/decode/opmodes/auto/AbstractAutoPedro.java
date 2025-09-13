package org.firstinspires.ftc.teamcode.decode.opmodes.auto;

import static org.firstinspires.ftc.teamcode.decode.subsystem.Common.mTelemetry;
import static org.firstinspires.ftc.teamcode.decode.subsystem.Common.robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.decode.subsystem.Common;
import org.firstinspires.ftc.teamcode.decode.subsystem.Robot;
import org.firstinspires.ftc.teamcode.decode.util.LoopUtil;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Disabled
public abstract class AbstractAutoPedro extends LinearOpMode {

    protected final void update() {
        robot.readSensors();
        Common.robot.run();
        robot.printTelemetry();
        mTelemetry.addData("Loop time (hertz)", LoopUtil.getLoopTimeInHertz());
        mTelemetry.update();
    }

    @Override
    public final void runOpMode() {
        Common.robot = new Robot(hardwareMap);
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot.actionScheduler.setUpdate(this::update);

        onInit();
        configure();

        if (isStopRequested()) return;

        waitForStart();

        resetRuntime();
        robot.drivetrain.setPose(getStartPose());

        onRun();
        Common.AUTO_END_POSE_PEDRO = robot.drivetrain.getPose();
    }

    protected void onInit() {
        robot.drivetrain.setPose(new Pose(8.291, 65.000, Math.toRadians(0)));
    }
    protected void configure() {}
    protected abstract Pose getStartPose();
    protected abstract void onRun();
}