package org.firstinspires.ftc.teamcode.decode.control.gainmatrices;

public class MovingAverageGains {
    public int count;

    public MovingAverageGains() {
        this(2); // default value for count
    }

    public MovingAverageGains(int count) {
        this.count = count;
    }
}