package com.pirateman.exercisemanager.interval;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pirateman.exercisemanager.R;
import com.pirateman.exercisemanager.exercise.Exercise;
import com.pirateman.exercisemanager.exercise.ExerciseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@TargetApi(26)
public class IntervalActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtReps;
    private EditText edtSets;
    private EditText edtWeight;

    private String currentDate;

    private Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
        exercise = (Exercise) getIntent().getSerializableExtra("exercise");
        TextView tvExerciseName = findViewById(R.id.tvExerciseName);
        tvExerciseName.setText(exercise.getName());

        TextView tvExerciseDate = findViewById(R.id.tvExerciseDate);

        currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        tvExerciseDate.setText(currentDate);

        edtReps = findViewById(R.id.edtReps);

        edtSets = findViewById(R.id.edtSets);

        edtWeight = findViewById(R.id.edtWeight);

        Button btnSubmitInterval = findViewById(R.id.btnIntervalSubmit);
        btnSubmitInterval.setOnClickListener(this);
        ImageButton imgAddInterval = findViewById(R.id.imgAddInterval);
        imgAddInterval.setOnClickListener(this);


        IntervalDao intervalDao = ExerciseDatabase.getINSTANCE(this).intervalDao();
        LiveData<List<Interval>> intervals = intervalDao.getIntervalsByExerciseId(exercise.getId());

        intervals.observe(this, new Observer<List<Interval>>() {
            @Override
            public void onChanged(@Nullable List<Interval> interval) {


            }
        });


        setUpMultiSliderListener();
    }

    public void setUpMultiSliderListener() {
//        MultiSlider slider = findViewById(R.id.msRange);

//        final TextView tvValue = findViewById(R.id.tvValue);
//        tvValue.setText(String.valueOf(slider.getMin()));

//        slider.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
//            @Override
//            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
//                tvValue.setText(String.valueOf(value));
//            }
//        });
    }

    @Override
    public void onClick(View v) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.btnIntervalSubmit:
                int reps = Integer.valueOf(edtReps.getText().toString());
                int sets = Integer.valueOf(edtSets.getText().toString());
                int weight = Integer.valueOf(edtWeight.getText().toString());

                Interval interval = new Interval();
                interval.setDate(currentDate);
                interval.setExerciseId(exercise.getId());
                interval.setReps(reps);
                interval.setSets(sets);
                interval.setWeight(weight);

                ExerciseDatabase.getINSTANCE(this).exerciseDao().insertInterval(interval);
                break;
            case R.id.imgAddInterval:

                fragmentTransaction.add(R.id.intervalRow, IntervalFragment.newInstance());

                fragmentTransaction.commit();

                break;

        }
    }
}
