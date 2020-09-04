package com.pirateman.exercisemanager.exercise

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.pirateman.exercisemanager.R

class ExerciseEditActivity : AppCompatActivity(), TextWatcher {
    private val exercise: Exercise? = null
    var listener: OnUpdateExerciseListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_edit)
        listener = this as OnUpdateExerciseListener
        val data = intent.extras
        val tvName = data!!.getString("tvName", "Name")
        val tvMethod = data.getString("tvMethod", "Method")
        val tvMuscleGroup = data.getString("tvMuscleGroup", "Muscle Group")
        val edtName = findViewById<EditText>(R.id.edtAddName)
        edtName.setText(tvName)
        val edtMethod = findViewById<EditText>(R.id.edtAddMethod)
        edtMethod.setText(tvMethod)
        val edtMuscleGroup = findViewById<EditText>(R.id.edtAddMuscleGroup)
        edtMuscleGroup.setText(tvMuscleGroup)
        edtName.addTextChangedListener(this)
        val btnEditSubmit = findViewById<Button>(R.id.btnSubmitNewExercise)
        btnEditSubmit.setOnClickListener { listener!!.updateExercise(Exercise(edtName.text.toString(), edtMuscleGroup.text.toString(), edtMethod.text.toString())) }
    }

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun afterTextChanged(editable: Editable) {}
}