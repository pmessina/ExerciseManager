package com.pirateman.exercisemanager

import android.content.Intent
import android.os.Bundle
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import androidx.viewbinding.ViewBinding
import com.pirateman.exercisemanager.databinding.ActivityExerciseCriteriaBinding
import org.koin.android.ext.android.inject


class ExerciseCriteriaActivity : AppCompatActivity() {

    private val binding by inject<ActivityExerciseCriteriaBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = binding.root
        setContentView(view)

        binding.btnAddInterval.setOnClickListener {
            val dur = binding.tvDuration.text.toString()
            val incl = binding.tvIncline.text.toString()
            val inte = binding.tvIntervals.text.toString()
            val spe = binding.tvSpeed.text.toString()
            //TODO: use jetpack navigation
            val intent = Intent(this@ExerciseCriteriaActivity, ExercisePlannerActivity::class.java)
            intent.putExtra("duration", dur)
            intent.putExtra("incline", incl)
            intent.putExtra("intervals", inte)
            intent.putExtra("speed", spe)
            startActivity(intent)
        }
    }

    fun addRow() {
        val textViewActivity = AutoCompleteTextView(this)
        textViewActivity.hint = "Activity"
        textViewActivity.textSize = 18f
        val textViewPace = AutoCompleteTextView(this)
        textViewPace.hint = "Pace"
        textViewPace.textSize = 18f
        val textViewIncline = AutoCompleteTextView(this)
        textViewIncline.hint = "Incline"
        textViewIncline.textSize = 18f
        val textViewDuration = AutoCompleteTextView(this)
        textViewDuration.hint = "Duration"
        textViewDuration.textSize = 18f

//        layout.addView(textViewActivity, setGrid(2, 0));
//        layout.addView(textViewPace, setGrid(2, 1));
//        layout.addView(textViewIncline, setGrid(2, 2));
//        layout.addView(textViewDuration, setGrid(2, 3));
    }

    fun setGrid(row: Int, col: Int): GridLayout.LayoutParams {
        val rowSpan = GridLayout.spec(row, 1)
        val colspan = GridLayout.spec(col, 1)
        return GridLayout.LayoutParams(rowSpan, colspan)
    }
}