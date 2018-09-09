package com.pirateman.exercisemanager.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pirateman.exercisemanager.R;

import io.apptik.widget.MultiSlider;

public class IntervalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        setUpMultiSliderListener();
    }

    public void setUpMultiSliderListener()
    {
        MultiSlider slider = findViewById(R.id.msRange);

        final TextView tvValue = findViewById(R.id.tvValue);
        tvValue.setText(String.valueOf(slider.getMin()));

        slider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
                tvValue.setText(String.valueOf(value));
            }
        });
    }

}
