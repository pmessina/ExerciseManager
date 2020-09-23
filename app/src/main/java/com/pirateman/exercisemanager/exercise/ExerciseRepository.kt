package com.pirateman.exercisemanager.exercise

import androidx.lifecycle.LiveData
import org.koin.core.KoinComponent
import org.koin.core.inject

class ExerciseRepository: KoinComponent {

    private val exerciseDatabase: ExerciseDatabase by inject()
//    private val exerciseDao: ExerciseDao?
//    val allExercises: LiveData<List<Exercise?>?>?

//    fun insertExercise(exercise: Exercise?) {
//        val executor: Executor = Executors.newSingleThreadExecutor()
//        executor.execute { exerciseDao!!.insertExercise(exercise) }
//    }
//
//    fun getExercise(id: Int): LiveData<Exercise?>? {
//        return exerciseDao!!.getExerciseById(id)
//    }

    fun getExercises(): LiveData<List<Exercise>> {
        return exerciseDatabase.exerciseDao().getAllExercises()
    }

    suspend fun updateExercise(exercise: Exercise) {
        exerciseDatabase.exerciseDao().updateExercise(exercise)
    }

//    init {
//
//        exerciseDao = exerciseDatabase?.exerciseDao()
//        allExercises = exerciseDao?.all
//    }
}