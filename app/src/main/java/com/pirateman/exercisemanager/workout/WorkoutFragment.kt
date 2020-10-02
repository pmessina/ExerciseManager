package com.pirateman.exercisemanager.workout

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_workout.*
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class WorkoutFragment : Fragment() {

    private val exerciseViewModel : ExerciseViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvExerciseDate.text = java.time.LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        tvExerciseName.text = exerciseViewModel.currentExercise.name ?: "No Exercise Name"

    }
}