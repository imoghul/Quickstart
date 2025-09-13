package org.firstinspires.ftc.teamcode.decode.opmodes;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.LEFT_BUMPER;
import static org.firstinspires.ftc.teamcode.decode.subsystem.Common.robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.decode.subsystem.Common;
import org.firstinspires.ftc.teamcode.decode.subsystem.Robot;

public class MainOpMode extends LinearOpMode {
    // Gamepads and the 'robot' class is imported to save lines and to import controls
    public static GamepadEx gamepadEx1, gamepadEx2;

    // Quick method that is used for better handling the controller
    public static boolean keyPressed(int gamepad, GamepadKeys.Button button) {
        return (gamepad == 2 ? gamepadEx2 : gamepadEx1).wasJustPressed(button);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Common.mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot = new Robot(hardwareMap);
        robot.drivetrain.startTeleopDrive();

        waitForStart();

        while (opModeIsActive()) {
            // Read sensors + gamepads:
            robot.readSensors();
            gamepadEx1.readButtons();
            gamepadEx2.readButtons();


            // Gamepad 1
            double slowMult = gamepadEx1.isDown(LEFT_BUMPER) ? 0.5 : 1;
            double slowTurningMult = gamepadEx1.isDown(LEFT_BUMPER) ? 0.3 : 1;

            robot.drivetrain.setTeleOpDrive(
                    gamepadEx1.getLeftY() * slowMult,
                    -gamepadEx1.getLeftX() * slowMult,
                    -gamepadEx1.getRightX() * slowTurningMult,
                    false
            );
        }
    }
}
