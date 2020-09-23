package com.pirateman.exercisemanager.selectedexercise

import android.app.Application
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.pirateman.exercisemanager.exercise.ExerciseDao
import androidx.lifecycle.LiveData
import com.pirateman.exercisemanager.exercise.Exercise
import com.pirateman.exercisemanager.exercise.ExerciseDatabase
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.exercise.ExerciseHolder
import com.pirateman.exercisemanager.selectedexercise.SelectedExerciseRepository


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectedExercisesAdapter(private val exerciseList: List<Exercise>, val context: Context) : RecyclerView.Adapter<SelectedExerciseHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedExerciseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_selected_exercise_item, parent, false) ?: parent.rootView
        return SelectedExerciseHolder(view)
    }

    override fun onBindViewHolder(holder: SelectedExerciseHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.tvSelectedExerciseName.text = exercise.name
        holder.view.tag = exercise

        //Toast.makeText(context, "$exercise", Toast.LENGTH_SHORT).show()

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }


}