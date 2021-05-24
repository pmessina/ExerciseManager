package com.pirateman.exercisemanager.exercise

import androidx.lifecycle.LiveData
import com.pirateman.exercisemanager.interval.ExerciseWithIntervals
import com.pirateman.exercisemanager.interval.Interval
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ExerciseRepository : KoinComponent {

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

    fun insertInterval(interval: Interval) {
        exerciseDatabase.intervalDao().insertInterval(interval)
    }

    private fun insertIntervalsList(intervals: List<Interval>) {
        exerciseDatabase.exerciseDao().insertIntervals(intervals)
    }

    fun insertIntervals(inte: ExerciseWithIntervals) {
        val id = exerciseDatabase.exerciseDao().insertExercise(inte.exercise)

        for (i in inte.intervals) {
            i.exerciseId = id
        }

        insertIntervalsList(inte.intervals)
    }


    fun getIntervalsByExercise(ex: Exercise): LiveData<List<Interval>> {

        return exerciseDatabase.intervalDao().getIntervalsByExerciseId(ex.id)

    }

//    init {
//
//        exerciseDao = exerciseDatabase?.exerciseDao()
//        allExercises = exerciseDao?.all
//    }
}