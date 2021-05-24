package com.pirateman.exercisemanager.interval

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.FragmentIntervalBinding
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import com.pirateman.exercisemanager.workout.WorkoutRepsSets
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class IntervalFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by viewModel()

    private val intervalBinding by inject<FragmentIntervalBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interval, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args ->
            exerciseViewModel.currentExerciseSelected.name.let {
                val wkRepsSets = args.getParcelable<WorkoutRepsSets>(it)

                wkRepsSets?.let { rsw ->
                    intervalBinding.edtReps.setText(rsw.reps)
                    intervalBinding.edtSets.setText(rsw.sets)
                    intervalBinding.edtWeight.setText(rsw.weight)
                }


            }
        }

        intervalBinding.imgAddExerciseInterval.setOnClickListener {

            //TODO: Move to interval fragment
            val reps = intervalBinding.edtReps.text.toString()
            val sets = intervalBinding.edtSets.text.toString()
            val weight = intervalBinding.edtWeight.text.toString()

            // Interval needs to know what exercise it belongs to in the workout
            val currentExercise = exerciseViewModel.currentExerciseSelected

            if (exerciseViewModel.intervals.containsKey(currentExercise)) {
                exerciseViewModel.intervals[currentExercise]?.add(
                    WorkoutRepsSets(
                        weight,
                        reps,
                        sets
                    )
                )
                (this.parentFragment as IntervalContainerFragment).addIntervalFragment(
                    exerciseViewModel.intervals[currentExercise]
                )
            } else {
                exerciseViewModel.intervals[currentExercise] =
                    arrayListOf(WorkoutRepsSets(weight, reps, sets))
            }


            //(this.parentFragment as IntervalContainerFragment).


        }


    }

}