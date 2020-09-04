package com.pirateman.exercisemanager.selectedexercise

import android.app.Application
import androidx.lifecycle.LiveData
import com.pirateman.exercisemanager.exercise.Exercise
import com.pirateman.exercisemanager.exercise.ExerciseDao
import com.pirateman.exercisemanager.exercise.ExerciseDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SelectedExerciseRepository internal constructor(application: Application?) {
    //private val exerciseDao: ExerciseDao?
    //val allExercises: LiveData<List<Exercise?>?>?

    fun insertExercise(exercise: Exercise?) {
//        val executor: Executor = Executors.newSingleThreadExecutor()
//        executor.execute { exerciseDao?.insertExercise(exercise) }
    }

    init {
       // val exerciseDatabase = ExerciseDatabase.getINSTANCE(application)
        //exerciseDao = exerciseDatabase?.exerciseDao()
        //allExercises = exerciseDao?.all
    }
}