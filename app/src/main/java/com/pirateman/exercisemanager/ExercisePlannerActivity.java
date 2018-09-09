package com.pirateman.exercisemanager;

import android.annotation.TargetApi;
import android.databinding.ObservableArrayList;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExercisePlannerActivity extends AppCompatActivity implements ActivityFragment.Callbacks {

    private  GridLayout layout;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_planner);

        layout = findViewById(R.id.glExerciseGenerator);

        //maximum values
        String duration = getIntent().getStringExtra("duration");
        String incline = getIntent().getStringExtra("incline");
        String intervals = getIntent().getStringExtra("intervals");
        String speed = getIntent().getStringExtra("speed");

        int dur = Integer.valueOf(duration);
        double incl = Double.valueOf(incline);
        //will determine the number of intervals to create
        int inte = Integer.valueOf(intervals);
        double spe = Double.valueOf(speed);

        List<ExerciseInterval> exerciseIntervals = new ArrayList<>();

        for (int i = 0; i < inte; i++)
        {

            ExerciseInterval exerciseInterval = new ExerciseInterval(spe, incl, dur);
            addFragment(exerciseInterval);
        }
//
//        List<ExerciseInterval> exerciseIntervals = new ArrayList<>();
//        exerciseIntervals.add(new ExerciseInterval(0, 3.3, 1.0, 2));
//        exerciseIntervals.add(new ExerciseInterval(0, 5.3, 2.0, 3));
//        exerciseIntervals.add(new ExerciseInterval(0, 1.0, 1.0, 2));
//        exerciseIntervals.add(new ExerciseInterval(0, 3.3, 2.5, 1));
//
//        for (ExerciseInterval interval : exerciseIntervals)
//        {
//
//            addFragment(interval);
//
////            addTextView(interval.getActivity(), interval.getRowId(), 0);
////            addTextView(String.valueOf(interval.getSpeed()), interval.getRowId(), 1);
////            addTextView(String.valueOf(interval.getIncline()), interval.getRowId(), 2);
////            addTextView(String.valueOf(interval.getDuration()), interval.getRowId(), 3);
//        }

    }

    public void addFragment(ExerciseInterval interval)
    {
        ActivityFragment fragment = new ActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exerciseInterval", interval);
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragment(ExerciseInterval interval)
    {
        ActivityFragment fragment = new ActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exerciseInterval", interval);
        fragment.setArguments(bundle);

//        ObservableArrayList<Object> user = new ObservableArrayList<>();
//        user.add("Google");
//        user.add("Inc.");
//        user.add(17);

        getSupportFragmentManager()
                .beginTransaction()

                .replace(R.id.flContainer, fragment)
                .commit();
    }

    public void addTextView(String str, int row, int col)
    {
        TextView textView = new TextView(this);

        textView.setText(str);
        textView.setTextSize(12f);

        GridLayout.Spec rowSpan = GridLayout.spec(row, 1);
        GridLayout.Spec colspan = GridLayout.spec(col, 1);

        GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
                rowSpan, colspan);
        layout.addView(textView, gridParam);

    }

//    public void addTextView(String str, int row, int col)
//    {
//        TextView textView = new TextView(this);
//
//        textView.setText(str);
//        textView.setTextSize(12f);
//
//        GridLayout.Spec rowSpan = GridLayout.spec(row, 1);
//        GridLayout.Spec colspan = GridLayout.spec(col, 1);
//
//        GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
//                rowSpan, colspan);
//        layout.addView(textView, gridParam);
//
//    }

    public void addCheckBox(int row, int col)
    {
        CheckBox checkBox = new CheckBox(this);

        GridLayout.Spec rowSpan = GridLayout.spec(row, 1);
        GridLayout.Spec colspan = GridLayout.spec(col, 1);

        GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
                rowSpan, colspan);
        layout.addView(checkBox, gridParam);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onReplaceFragmentClick(View v)
    {
        ExerciseInterval newInterval = new ExerciseInterval();
        replaceFragment(newInterval);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onAddFragmentClick(View v)
    {
        ExerciseInterval newInterval = new ExerciseInterval();
        addFragment(newInterval);
    }


    @Override
    public void onButtonClicked()
    {
        replaceFragment(new ExerciseInterval());
    }
}
