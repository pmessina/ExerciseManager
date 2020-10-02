package com.pirateman.exercisemanager.selectedexercise

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.exercise.Exercise
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import kotlinx.android.synthetic.main.recycler_view_selected_exercise_item.view.*


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectedExercisesAdapter(private val exerciseList: ArrayList<Exercise>, private val context: Context, private val exerciseViewModel: ExerciseViewModel) : RecyclerView.Adapter<SelectedExerciseHolder>() /*, View.OnClickListener*/ {

//    view.setOnClickListener(this)
//
//    imgDeleteExercise.setOnClickListener(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedExerciseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_selected_exercise_item, parent, false)
                ?: parent.rootView
        return SelectedExerciseHolder(view)
    }

    override fun onBindViewHolder(holder: SelectedExerciseHolder, position: Int) {
        val exercise = exerciseList[position]
        holder.tvSelectedExerciseName.text = exercise.name
        holder.view.tag = exercise

        holder.view.setOnClickListener {
            val currentEx = it.tag as Exercise
            exerciseViewModel.currentExercise = currentEx
            Navigation.findNavController(holder.view).navigate(R.id.workoutFragment)
        }


        holder.view.imgDelete.setOnClickListener {
            exerciseViewModel.setSelectedExercise(exercise, false)
            exerciseList.removeAt(position)
            notifyItemRemoved(position)
        }
    }


//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onClick(itemView: View) {
//
//        val ex = itemView.tag as Exercise
//        exerciseViewModel.currentExercise = ex
//
//        if (itemView == itemView.imgDelete) {
//            if (itemCount > 0) {
//
//                exerciseViewModel.setSelectedExercise(ex, false)
//
//
//            }
//        }
//        else{
//
//        }
//
//
//
//
//        if (itemView.equals(imgDeleteExercise)){
//
//        }
//
//        //val context = itemView.context
//
//    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }


}