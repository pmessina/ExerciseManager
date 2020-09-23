package com.pirateman.exercisemanager.selectedexercise

import android.app.Application
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.pirateman.exercisemanager.exercise.ExerciseDao
import androidx.lifecycle.LiveData
import com.pirateman.exercisemanager.exercise.Exercise
import com.pirateman.exercisemanager.exercise.ExerciseDatabase
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.multiselector.MultiSelector
import com.bignerdranch.android.multiselector.SwappingHolder
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import com.pirateman.exercisemanager.selectedexercise.SelectedExerciseRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectedExerciseHolder(val view: View) : KoinComponent, RecyclerView.ViewHolder(view), View.OnClickListener {

    val tvSelectedExerciseName = view.findViewById(R.id.tvSelectedExerciseName) as TextView


    init{
        view.isClickable = true
        view.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(itemView: View) {

        val context = itemView.context

    }


}