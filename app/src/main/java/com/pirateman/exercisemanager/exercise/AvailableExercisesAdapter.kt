package com.pirateman.exercisemanager.exercise

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.FragmentAvailableExercisesBinding
import com.pirateman.exercisemanager.databinding.RecyclerViewExerciseItemBinding
import com.pirateman.exercisemanager.databinding.RecyclerViewSelectedExerciseItemBinding

@RequiresApi(Build.VERSION_CODES.M)
class AvailableExercisesAdapter(private val exerciseViewModel: ExerciseViewModel, val context: Context) :
    RecyclerView.Adapter<ExerciseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {

        val binding = RecyclerViewExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.recycler_view_exercise_item, parent, false)
//            ?: parent.rootView

        return ExerciseHolder(binding, exerciseViewModel)
    }


    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        val exercise = exerciseViewModel.exerciseList[position]

        holder.tvName.text = exercise.name
        holder.itemView.tag = exercise

        //Toast.makeText(context, "$exercise", Toast.LENGTH_SHORT).show()

        if (exercise.selected) {
            val color = context.resources.getColor(R.color.colorPrimary, context.theme)
            val drawable = ColorDrawable(color)
            holder.itemView.setBackgroundColor(color)
            holder.selectionModeBackgroundDrawable = drawable

        } else {
            val color = context.resources.getColor(R.color.white, context.theme)
            holder.itemView.setBackgroundColor(color)
            val drawable = ColorDrawable(color)
            holder.selectionModeBackgroundDrawable = drawable
        }
    }

    override fun getItemCount(): Int {
        return exerciseViewModel.exerciseList.size
    }


}
