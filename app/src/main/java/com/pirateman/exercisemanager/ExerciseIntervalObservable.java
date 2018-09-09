package com.pirateman.exercisemanager;

import android.databinding.ObservableField;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExerciseIntervalObservable implements Serializable
{
    private ObservableField<Integer> rowId;
    private ObservableField<String> activity;
    private ObservableField<Double> speed;
    private ObservableField<Double> incline;
    private ObservableField<Integer> duration;

    public ExerciseIntervalObservable()
    {
        this.randomize();
    }

    public ExerciseIntervalObservable(int rowId, double speed, double incline, int duration)
    {
        this.rowId.set(rowId);
        this.speed.set(speed);
        this.incline.set(incline);
        this.duration.set(duration);

        this.activity.set(setActivity(speed));
    }

    public int getDuration() {
        return duration.get();
    }

    public double getIncline() {
        return incline.get();
    }

    public double getSpeed() {
        return speed.get();
    }

    public String getActivity() {
        return activity.get();
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
        return rowId.get();
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public void setIncline(double incline) {
        this.incline.set(incline);
    }

    public void setSpeed(double speed) {
        this.speed.set(speed);
    }


    private void randomize()
    {
        double[] incline = {0.0, 0.5, 1.0, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0, 11.5, 12.0, 12.5, 13.0};
        double[] speed = {0.0, 0.5, 1.0, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11, 11.5, 12.0};

        int speedIndex = ThreadLocalRandom.current().nextInt(1, speed.length);
        int inclineIndex = ThreadLocalRandom.current().nextInt(0, incline.length);
        int randDuration = ThreadLocalRandom.current().nextInt(3, 8);

        double selectedSpeed = speed[speedIndex];
        double selectedIncline = incline[inclineIndex];

        this.speed.set(selectedSpeed);
        this.incline.set(selectedIncline);
        this.duration.set(randDuration);
        this.activity.set(setActivity(selectedSpeed));
    }
}
