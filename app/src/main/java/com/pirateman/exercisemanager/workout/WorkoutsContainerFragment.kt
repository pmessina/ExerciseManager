package com.pirateman.exercisemanager.workout

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.FragmentWorkoutsContainerBinding
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class WorkoutsContainerFragment : Fragment() {

    val exerciseViewModel: ExerciseViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workouts_container, container, false)
    }

    @SuppressLint("LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workoutContainerBinding = FragmentWorkoutsContainerBinding.bind(view)

        val selectedExercises = exerciseViewModel.getSelectedExercisesList()

        val workoutGroups = HashMap<WorkoutHeader, MutableList<WorkoutContent>>()
//
//        val workoutRepsSets = ArrayList<WorkoutRepsSets>()
//
        for (ex in selectedExercises) {
//
            val dbIntervals = exerciseViewModel.getIntervalsByExercise(ex)


            workoutGroups[WorkoutHeader(ex)] = mutableListOf()

//
//            //Toast.makeText(requireContext(), "${dbIntervals.value?.size ?: 0} intervals for ${ex.name}", Toast.LENGTH_LONG).show()
            dbIntervals.observe(viewLifecycleOwner, Observer {

                for (interval in it){
                    workoutGroups[WorkoutHeader(ex)] = mutableListOf(WorkoutContent(interval.reps.toString(), interval.sets.toString(), interval.weight.toString()))
                }

                Log.i("WorkoutsContainerFragment", "${it.size} intervals for ${ex.name}")
                //Toast.makeText(requireContext(), "${it.size} intervals for ${ex.name}", Toast.LENGTH_LONG).show()
            })
//
//            val exIntervals = exerciseViewModel.intervals[ex] ?: ArrayList()
//
//            //Populate workout expandable list with new intervals
//            if (exIntervals.size > 0) {
//                workoutGroups.add(WorkoutNameExpandableGroup(ex, exIntervals))
//            } else {
//                workoutGroups.add(WorkoutNameExpandableGroup(ex, listOf(WorkoutRepsSets("", "", ""))))
//            }
//        }
//
//            val interval = exerciseViewModel.intervals[ex.name] ?: WorkoutRepsSets("", "", "")
//
//            workoutGroups.add(WorkoutNameExpandableGroup(ex.name ?: "No Name", listOf(WorkoutRepsSets(interval.weight, interval.sets, interval.reps))))
//
//
//        val list = mutableListOf(
//                WorkoutNameExpandableGroup("Bicep Curls", listOf(WorkoutRepsSets("25", "3", "10"))),
//                WorkoutNameExpandableGroup("Push Ups", listOf(WorkoutRepsSets("0", "3", "20")))
//        )
        }

        //TODO: pass in context if needed

//        val hashMap = hashMapOf(WorkoutHeader(
//                                    Exercise("bicep curls", "biceps")
//                                ) to
//                                    mutableListOf(
//                                        WorkoutContent("10", "10", "3"),
//                                        WorkoutContent("10", "10", "3"),
//                                        WorkoutContent("10", "10", "3")
//                                    )
//                                )
        workoutContainerBinding.rvWorkoutsContainer.adapter = WorkoutsExpandableListAdapter(workoutGroups)
        workoutContainerBinding.rvWorkoutsContainer.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        workoutContainerBinding.rvWorkoutsContainer.setHasFixedSize(true)


    }
}




