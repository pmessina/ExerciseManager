package com.pirateman.exercisemanager.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class ExerciseViewModel() : ViewModel(), KoinComponent {

    val exerciseRepository: ExerciseRepository by inject()
    val exerciseAdapter: ExerciseAdapter by inject()
//    val exercises: LiveData<List<Exercise>>

    //    public ExerciseAdapter getExerciseAdapter() {
    //        return exerciseAdapter;
    //    }
    fun setUpExerciseAdapter(exercises: List<Exercise?>?) {

        //exerciseAdapter.setExerciseList(exercises);
//        recyclerView.setAdapter(exerciseAdapter);
    }

    fun getAllExercises(): LiveData<List<Exercise>> {
        val exercises = exerciseRepository.getExercises()

        return exercises

    }


    val exerciseList: List<Exercise>

    fun setExercise(exercise: Exercise) {
        //exerciseList.add(exercise)
        // exerciseAdapter.notifyDataSetChanged();
    }

    fun setSelectedExercise(exercise: Exercise) {
        //selectedExercises.add(exercise)
        //exerciseAdapter.notifyDataSetChanged();
    }

    //val selectedExercises: List<Exercise>

    fun updateExercise(id: Int) {
        //exerciseAdapter.notifyItemChanged(id)
    }

    fun removeExercise(id: Int) {
        //exerciseList.removeAt(id)
        //exerciseAdapter.notifyDataSetChanged();
    }

    fun insertExercise(exercise: Exercise?) {
        //exerciseRepository.insertExercise(exercise)
    }

//    fun getSelectedExercise(id: Int): LiveData<Exercise> {
//        return exerciseRepository.getExercise(id)
//    }

    fun getSelectedExerciseAt(id: Int?): Exercise {
        return Exercise("", "", "") //exerciseAdapter.getExerciseList().get(id);
    }

    companion object {
        //private val exerciseList: MutableList<Exercise>
        //private val selectedExercises = ArrayList<Exercise>()
    }

    init {
        //exerciseAdapter = new ExerciseAdapter(R.layout.content_exercise, this);
        //exerciseRepository = ExerciseRepository(application)
       //exercises = exerciseRepository.allExercises
        exerciseList = ArrayList()
    }
}