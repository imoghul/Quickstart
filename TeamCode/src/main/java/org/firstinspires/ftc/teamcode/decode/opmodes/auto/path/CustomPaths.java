package org.firstinspires.ftc.teamcode.decode.opmodes.auto.path;

import static org.firstinspires.ftc.teamcode.decode.subsystem.Common.robot;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

@Config
public class CustomPaths {
    public static PathBuilder builder = new PathBuilder(robot.drivetrain);
    CurveRightTest CRT = new CurveRightTest();

    public static class CurveRightTest {
        public Pose[] controlPoints = {
                new Pose(48.000, 80.000),
                new Pose(25.000, 56.000)
        };

        public Pose[] endPoints = {
                new Pose(30.000, 80.000),
                new Pose(36.000, 35.000)
        };
    }
        public PathChain straightLine = builder
                .addPath(new BezierLine(new Pose(8.291, 65.000), CRT.endPoints[0]))
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        public PathChain curveRight = builder
                .addPath(
                        new BezierCurve(
                                CRT.endPoints[0],
                                CRT.controlPoints[0],
                                CRT.controlPoints[1],
                                CRT.endPoints[1]
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
}
