package com.pirateman.exercisemanager.exercise

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.KoinComponent
import org.koin.core.inject

class ExerciseDatabaseWorker(context: Context, workerParams: WorkerParameters) : KoinComponent, CoroutineWorker(context, workerParams) {

    //private val database: ExerciseDatabase by inject()
    private val exerciseDao: ExerciseDao by inject()
    override suspend fun doWork(): Result {

        val exerciseList = arrayOf(
                Exercise("Bicep Curls", "Biceps", "Barbells"),
                Exercise("Tricep Extension", "Triceps", "Barbells"),
                Exercise("Skull Crushers", "Triceps", "Barbells"),
                Exercise("Leg Press", "Quadriceps", "Machine"),
                Exercise("Back Extension", "Back", "Barbells"),
                Exercise("Shoulder Press", "Shoulders", "Barbells"),
                Exercise("Bent over Row", "Shoulders", "Barbells"),
                Exercise("Front Raises", "Shoulders", "Barbells"),
                Exercise("Lateral Raises", "Shoulders", "Barbells"),
                Exercise("Reverse Bridge Dips", "Triceps", "Weightless"),
                Exercise("Pushups", "Chest", "Weightless"),
                Exercise("Chest Fly", "Chest", "Barbells"),
                Exercise("One Arm Pushup Left/Right", "Abs", "Weightless"),
                Exercise("Hi Lo Plank", "Abs", "Weightless"),
                Exercise("High Side Planks Left/Right", "Arms", "Weightless"),
                Exercise("Reverse Plank Dips", "Triceps", "Weightless")
        )
//
//        val exerciseDao = database.exerciseDao()

        exerciseDao.insertAll(*exerciseList)

        return Result.success()

    }
}