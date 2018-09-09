package com.pirateman.exercisemanager;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExerciseInterval implements Serializable
{
    private int rowId;
    private String activity;
    private double speed;
    private double incline;
    private int duration;

    public ExerciseInterval()
    {
        this.randomize();
    }

    public ExerciseInterval(double speed, double incline, int duration)
    {
        randomize(speed, incline, duration);
    }

    public ExerciseInterval(int intervals, double speed, double incline, int duration)
    {
        randomize(speed, incline, duration, intervals);
    }


    public ExerciseInterval(int rowId, int intervals, double speed, double incline, int duration)
    {
        this.rowId = rowId;
        this.speed = speed;
        this.incline = incline;
        this.duration = duration;

        this.activity = setActivity(speed);
    }

    public int getDuration() {
        return duration;
    }

    public double getIncline() {
        return incline;
    }

    public double getSpeed() {
        return speed;
    }

    public String getActivity() {
        return activity;
    }

    //The activity field is set based on the speed
    private String setActivity(double speed)
    {
        if (speed < 3.0)
            return "Walk";
        if (speed >= 3.0 && speed < 5.0)
            return "Jog";
        if (speed >= 5.0 && speed < 7.0)
            return "Run";
        return "Sprint";
    }

    public int getRowId() {
        return rowId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setIncline(double incline) {
        this.incline = incline;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    //randomize based on hard coded values
    private void randomize()
    {
        double[] incline = {0.0, 0.5, 1.0, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0, 11.5, 12.0, 12.5, 13.0, 13.5, 14.0, 15.0};
        double[] speed = {0.0, 0.5, 1.0, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11, 11.5, 12.0};

        int speedIndex = ThreadLocalRandom.current().nextInt(1, speed.length);
        int inclineIndex = ThreadLocalRandom.current().nextInt(0, incline.length);
        int randDuration = ThreadLocalRandom.current().nextInt(3, 8);

        double selectedSpeed = speed[speedIndex];
        double selectedIncline = incline[inclineIndex];

        this.speed = selectedSpeed;
        this.incline = selectedIncline;
        this.duration = randDuration;
        this.activity = setActivity(selectedSpeed);
    }

    private void randomize(double speed, double incline, int duration)
    {
        double randSpeed = ThreadLocalRandom.current().nextDouble(1, speed);
        double roundedRandSpeed = Math.round(randSpeed * 2) / 2.0;

        double randIncline = ThreadLocalRandom.current().nextDouble(0, incline);
        double roundedIncline = Math.round(randIncline * 2) / 2.0;

        int randDuration = ThreadLocalRandom.current().nextInt(1, duration);

        this.speed = roundedRandSpeed;
        this.incline = roundedIncline;
        this.duration = randDuration;
        this.activity = setActivity(randSpeed);
    }

    private void randomize(double speed, double incline, int duration, int intervals)
    {
        double randSpeed = ThreadLocalRandom.current().nextDouble(1, speed);
        double roundedRandSpeed = Math.round(randSpeed * 2) / 2.0;

        double randIncline = ThreadLocalRandom.current().nextDouble(0, incline);
        double roundedIncline = Math.round(randIncline * 2) / 2.0;

        int randDuration = ThreadLocalRandom.current().nextInt(1, duration);

        this.speed = roundedRandSpeed;
        this.incline = roundedIncline;
        this.duration = randDuration;
        this.activity = setActivity(randSpeed);
    }
}
