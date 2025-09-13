package org.firstinspires.ftc.teamcode.decode.control.gainmatrices;

public class HSV {
    public double hue;
    public double saturation;
    public double value;

    public HSV() {
        this(0.0, 0.0, 0.0);
    }

    public HSV(double hue, double saturation, double value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public boolean inRange(HSV min, HSV max) {
        boolean hueInRange = (min.hue <= hue) && (hue <= max.hue);
        boolean saturationInRange = (min.saturation <= saturation) && (saturation <= max.saturation);
        boolean valueInRange = (min.value <= value) && (value <= max.value);

        return hueInRange && saturationInRange && valueInRange;
    }
}