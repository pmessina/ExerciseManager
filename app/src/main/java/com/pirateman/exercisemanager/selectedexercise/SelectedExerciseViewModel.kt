package com.pirateman.exercisemanager.selectedexercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pirateman.exercisemanager.exercise.Exercise

class SelectedExerciseViewModel(application: Application?) : AndroidViewModel(application!!) {
    var selectedExerciseRepository: SelectedExerciseRepository
    //val exercises: LiveData<List<Exercise?>?>?

    fun insertExercise(exercise: Exercise?) {
        selectedExerciseRepository.insertExercise(exercise)
    }

    init {
        selectedExerciseRepository = SelectedExerciseRepository(application)
        //exercises = selectedExerciseRepository.allExercises
    }
}