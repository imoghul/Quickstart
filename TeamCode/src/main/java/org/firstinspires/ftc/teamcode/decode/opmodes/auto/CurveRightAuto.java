package org.firstinspires.ftc.teamcode.decode.opmodes.auto;

import static org.firstinspires.ftc.teamcode.decode.subsystem.Common.robot;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.decode.opmodes.auto.path.CustomPaths;
import org.firstinspires.ftc.teamcode.decode.util.DrivePoseLoggingAction;
import org.firstinspires.ftc.teamcode.decode.util.Actions;
import org.firstinspires.ftc.teamcode.decode.util.FollowPathAction;

public class CurveRightAuto extends AbstractAutoPedro {
    public final CustomPaths C_P = new CustomPaths();
    private final Follower f = robot.drivetrain;

    @Override
    protected Pose getStartPose() {
        return null;
    }

    @Override
    protected void onRun() {
        driveStraight();
        curveRight();
    }

    private void driveStraight() {
        C_P.straightLine.getPath(0).setTValueConstraint(.96);

        robot.actionScheduler.addAction(
                new SequentialAction(
                        new DrivePoseLoggingAction(f, "before_drive_straight_line", true),
                        new ParallelAction(
                                new Actions.CallbackAction(new InstantAction(() -> f.setMaxPower(0.92)), C_P.straightLine, 0.85, 0, f),
                                new FollowPathAction(f, C_P.straightLine)
                        ),
                        new DrivePoseLoggingAction(f, "after_drive_straight_line")
                )
        );

        robot.actionScheduler.runBlocking();
    }

    private void curveRight() {
        C_P.curveRight.getPath(0).setTValueConstraint(.88);

        robot.actionScheduler.addAction(
                new SequentialAction(
                        new DrivePoseLoggingAction(f, "before_curve", true),
                        new ParallelAction(
                                new Actions.CallbackAction(new InstantAction(() -> f.setMaxPower(0.92)), C_P.curveRight, 0.6, 0, f),
                                new FollowPathAction(f, C_P.curveRight)
                        ),
                        new DrivePoseLoggingAction(f, "after_curve")
                )
        );

        robot.actionScheduler.runBlocking();
    }
}
