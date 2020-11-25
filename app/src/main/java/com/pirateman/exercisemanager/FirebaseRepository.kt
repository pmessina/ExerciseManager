package com.pirateman.exercisemanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.pirateman.exercisemanager.exercise.Exercise
import org.koin.core.KoinComponent
import org.koin.core.inject

class FirebaseRepository : KoinComponent {

    private val firebaseTag = FirebaseRepository::class.qualifiedName

    lateinit var query: Query

    private var size: Int = 0

    private val firestore: FirebaseFirestore by inject()

    fun addExercise(exercise: Exercise) {
        exercise.name?.let {
            firestore.collection("exercises")
                .document(it)
                .set(exercise)
                .addOnSuccessListener { Log.d(firebaseTag, "${exercise.name} successfully written") }
                .addOnFailureListener { e -> Log.e(firebaseTag, "Error writing document snapshot", e) }
        }
    }

    fun getExercises() {
        query = firestore.collection("exercises")
    }

    fun getExerciseCount(): Int{
        firestore.collection("exercises").get().addOnSuccessListener {
            size = it.size()
        }

        return size
    }
}

class FirebaseWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params){

    private val firebaseTag = FirebaseWorker::class.qualifiedName

    private val firebaseRepository = FirebaseRepository()

    private val exerciseList = arrayOf(
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

    override suspend fun doWork(): Result {

        Log.i(firebaseTag, "Number of exercise documents: ${firebaseRepository.getExerciseCount()}")

        for (ex in exerciseList){
            firebaseRepository.addExercise(ex)
        }

        return Result.success()
    }
}