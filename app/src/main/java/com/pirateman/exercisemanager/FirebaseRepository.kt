package com.pirateman.exercisemanager

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.pirateman.exercisemanager.exercise.Exercise
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.ext.getScopeName

class FirebaseRepository : KoinComponent {

    private val firebaseTag = FirebaseRepository::class.qualifiedName

    lateinit var query: Query

    val firestore: FirebaseFirestore by inject()

    fun addExercise(exercise: Exercise) {
        firestore.collection("exercises")
                .document("BicepCurls")
                .set(exercise)
                .addOnSuccessListener { Log.d(firebaseTag, "Document Snapshot successfully written") }
                .addOnFailureListener { e -> Log.e(firebaseTag, "Error writing document snapshot", e) }
    }

    fun getExercises() {
        query = firestore.collection("exercises")


    }
}