package com.pirateman.exercisemanager

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.GridLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class ExercisePlannerActivity : AppCompatActivity(), ActivityFragment.Callbacks {
    private var layout: GridLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_planner)
        layout = findViewById(R.id.glExerciseGenerator)

        //maximum values
        val duration = intent.getStringExtra("duration")
        val incline = intent.getStringExtra("incline")
        val intervals = intent.getStringExtra("intervals")
        val speed = intent.getStringExtra("speed")
        val dur = Integer.valueOf(duration)
        val incl = java.lang.Double.valueOf(incline)
        //will determine the number of intervals to create
        val inte = Integer.valueOf(intervals)
        val spe = java.lang.Double.valueOf(speed)
        val exerciseIntervals: List<ExerciseInterval> = ArrayList()
        for (i in 0 until inte) {
            val exerciseInterval = ExerciseInterval(spe, incl, dur)
            addFragment(exerciseInterval)
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

    fun addFragment(interval: ExerciseInterval?) {
        val fragment = ActivityFragment()
        val bundle = Bundle()
        bundle.putSerializable("exerciseInterval", interval)
        fragment.arguments = bundle
        supportFragmentManager
                .beginTransaction()
                .add(R.id.flContainer, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun replaceFragment(interval: ExerciseInterval?) {
        val fragment = ActivityFragment()
        val bundle = Bundle()
        bundle.putSerializable("exerciseInterval", interval)
        fragment.arguments = bundle

//        ObservableArrayList<Object> user = new ObservableArrayList<>();
//        user.add("Google");
//        user.add("Inc.");
//        user.add(17);
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.flContainer, fragment)
                .commit()
    }

    fun addTextView(str: String?, row: Int, col: Int) {
        val textView = TextView(this)
        textView.text = str
        textView.textSize = 12f
        val rowSpan = GridLayout.spec(row, 1)
        val colspan = GridLayout.spec(col, 1)
        val gridParam = GridLayout.LayoutParams(
                rowSpan, colspan)
        layout!!.addView(textView, gridParam)
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
    fun addCheckBox(row: Int, col: Int) {
        val checkBox = CheckBox(this)
        val rowSpan = GridLayout.spec(row, 1)
        val colspan = GridLayout.spec(col, 1)
        val gridParam = GridLayout.LayoutParams(
                rowSpan, colspan)
        layout!!.addView(checkBox, gridParam)
    }

    fun onReplaceFragmentClick(v: View?) {
        val newInterval = ExerciseInterval()
        replaceFragment(newInterval)
    }

    fun onAddFragmentClick(v: View?) {
        val newInterval = ExerciseInterval()
        addFragment(newInterval)
    }

    override fun onButtonClicked() {
        replaceFragment(ExerciseInterval())
    }
}