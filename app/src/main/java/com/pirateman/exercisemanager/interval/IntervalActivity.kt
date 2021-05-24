package com.pirateman.exercisemanager.interval

import android.annotation.TargetApi
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.ActivityIntervalBinding
import com.pirateman.exercisemanager.exercise.Exercise
import org.koin.android.ext.android.inject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@TargetApi(26)
class IntervalActivity : AppCompatActivity(), View.OnClickListener {
    private var edtReps: EditText? = null
    private var edtSets: EditText? = null
    private var edtWeight: EditText? = null
    private var currentDate: String? = null
    private var exercise: Exercise? = null

    private val intervalBinding by inject<ActivityIntervalBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interval)
        exercise = intent.getSerializableExtra("exercise") as Exercise?

        intervalBinding.tvExerciseName.text = exercise!!.name

        currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
        intervalBinding.tvExerciseDate.text = currentDate

        val btnSubmitInterval = findViewById<Button>(R.id.btnWorkoutSubmit)
        btnSubmitInterval.setOnClickListener(this)
        intervalBinding.imgAddInterval.setOnClickListener(this)
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
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        when (v.id) {
            R.id.btnWorkoutSubmit -> {
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
                //fragmentTransaction.add(R.id.intervalRow, IntervalFragment.newInstance())
                fragmentTransaction.commit()
            }
        }
    }
}