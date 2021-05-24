package com.pirateman.exercisemanager.selectedexercise

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.databinding.RecyclerViewSelectedExerciseItemBinding

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectedExerciseHolder(private val viewBinding: RecyclerViewSelectedExerciseItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    //For potential future use
    val tvSelectedExerciseName = viewBinding.tvSelectedExerciseName
    val imgDeleteExercise = viewBinding.imgDelete

    init {
        viewBinding.root.isClickable = true

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