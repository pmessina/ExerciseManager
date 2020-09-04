package com.pirateman.exercisemanager.interval

import android.annotation.TargetApi
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.exercise.Exercise
import com.pirateman.exercisemanager.exercise.ExerciseDatabase
import kotlinx.android.synthetic.main.activity_interval.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@TargetApi(26)
class IntervalActivity : AppCompatActivity(), View.OnClickListener {
    private var edtReps: EditText? = null
    private var edtSets: EditText? = null
    private var edtWeight: EditText? = null
    private var currentDate: String? = null
    private var exercise: Exercise? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interval)
        exercise = intent.getSerializableExtra("exercise") as Exercise?

        tvExerciseName.text = exercise!!.name

        currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
        tvExerciseDate.text = currentDate

        val btnSubmitInterval = findViewById<Button>(R.id.btnIntervalSubmit)
        btnSubmitInterval.setOnClickListener(this)
        imgAddInterval.setOnClickListener(this)
        //val intervalDao = ExerciseDatabase.getINSTANCE(this)?.intervalDao()
        //val intervals = intervalDao?.getIntervalsByExerciseId(exercise!!.id)
        //intervals!!.observe(this, Observer { })
        setUpMultiSliderListener()
    }

    fun setUpMultiSliderListener() {
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

    override fun onClick(v: View) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        when (v.id) {
            R.id.btnIntervalSubmit -> {
                val reps = Integer.valueOf(edtReps!!.text.toString())
                val sets = Integer.valueOf(edtSets!!.text.toString())
                val weight = Integer.valueOf(edtWeight!!.text.toString())
                val interval = Interval()
                interval.date = currentDate
                interval.exerciseId = exercise!!.id
                interval.reps = reps
                interval.sets = sets
                interval.weight = weight
                //ExerciseDatabase.getINSTANCE(this)?.exerciseDao()?.insertInterval(interval)
            }
            R.id.imgAddInterval -> {
                fragmentTransaction.add(R.id.intervalRow, IntervalFragment.Companion.newInstance())
                fragmentTransaction.commit()
            }
        }
    }
}