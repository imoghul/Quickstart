package org.firstinspires.ftc.teamcode.decode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.geometry.Pose;

@Config
public final class Common {
    public static Pose AUTO_END_POSE = null;

    public static final double
            LEFT = Math.toRadians(180),
            FORWARD = Math.toRadians(90),
            RIGHT = Math.toRadians(0),
            BACKWARD = Math.toRadians(270),
            SERVO_25_KG_MIN = 0,
            SERVO_25_KG_MAX = 270,
            SERVO_45_KG_MIN = 0,
            SERVO_45_KG_MAX = 270,
            SERVO_AXON_MAX_1 = 270,
            SERVO_AXON_MIN = 0,
            SERVO_AXON_MAX_2 = 355;

    public static final double MAX_VOLTAGE = 13;

    public static Robot robot;
}
