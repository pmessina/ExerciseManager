package com.pirateman.exercisemanager.workout

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.FragmentWorkoutBinding
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class WorkoutFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workoutBinding:FragmentWorkoutBinding = FragmentWorkoutBinding.bind(view)

        workoutBinding.tvExerciseDate.text =
            java.time.LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        workoutBinding.tvExerciseName.text =
            exerciseViewModel.currentExercise.name ?: "No Exercise Name"


    }
}