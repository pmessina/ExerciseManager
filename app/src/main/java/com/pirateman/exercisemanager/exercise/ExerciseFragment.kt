package com.pirateman.exercisemanager.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pirateman.exercisemanager.R
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class ExerciseFragment : Fragment() {

    val exerciseViewModel: ExerciseViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        exerciseViewModel.getAllExercises().observe(this.viewLifecycleOwner, Observer {

        })

//        exercises.observe(, androidx.lifecycle.Observer {
//            t ->
//        })

//        ExerciseItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_exercise_item, container, false);
//
//        Exercise exercise = new Exercise();
//        exercise.setName("Chest Press");
//        exercise.setMuscleGroup("Chest");
//        exercise.setMethod("Barbells");
//
//        binding.setExerciseRecord(exercise);
//
//        return binding.getRoot();
        return inflater.inflate(R.layout.recycler_view_exercise_item, container, false)
    }
}