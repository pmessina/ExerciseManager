package com.pirateman.exercisemanager.selectedexercise

import android.os.Build
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.exercise.Exercise
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import kotlinx.android.synthetic.main.recycler_view_selected_exercise_item.view.*
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.core.KoinComponent

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectedExerciseHolder(val view: View) : RecyclerView.ViewHolder(view) /*, View.OnClickListener*/ {

    val tvSelectedExerciseName = view.findViewById(R.id.tvSelectedExerciseName) as TextView
    val imgDeleteExercise = view.findViewById(R.id.imgDelete) as ImageButton

    init {
        view.isClickable = true

//        imgDeleteExercise.setOnClickListener {
//            (itemView as ViewGroup).removeView(view)
//        }

    }



//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onClick(itemView: View) {
//
//        val ex = itemView.tag as Exercise
//        //exerciseViewModel.currentExercise = ex
//
//        if (itemView == imgDeleteExercise) {
//            //adapterPosition
////            if (itemCount > 0) {
//
//                //exerciseViewModel.setSelectedExercise(ex, false)
//
//                //Id should be the same as the position in the recyclerview
//
////                exerciseList.removeAt(ex.id)
////                notifyItemRemoved(ex.id)
////            }
//        }
//        else{
//            Navigation.findNavController(itemView).navigate(R.id.workoutFragment)
//        }
//
//
//
////
////        if (itemView.equals(imgDeleteExercise)){
////
////        }
//
//        //val context = itemView.context
//
//    }



}