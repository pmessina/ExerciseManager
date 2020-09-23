package com.pirateman.exercisemanager.exercise

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R
import org.koin.core.KoinComponent

@RequiresApi(Build.VERSION_CODES.M)
class AvailableExercisesAdapter(private val exerciseList: List<Exercise>, val context: Context) : KoinComponent, RecyclerView.Adapter<ExerciseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_exercise_item, parent, false)
                ?: parent.rootView

        return ExerciseHolder(view)
    }


    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.tvName.text = exercise.name
        holder.view.tag = exercise

        //Toast.makeText(context, "$exercise", Toast.LENGTH_SHORT).show()

        if (exercise.selected) {
            val color = context.resources.getColor(R.color.colorPrimary, context.theme)
            val drawable = ColorDrawable(color)
            holder.view.setBackgroundColor(color)
            holder.selectionModeBackgroundDrawable = drawable

        } else {
            val color = context.resources.getColor(R.color.white, context.theme)
            holder.view.setBackgroundColor(color)
            val drawable = ColorDrawable(color)
            holder.selectionModeBackgroundDrawable = drawable
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }


}
