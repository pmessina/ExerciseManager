package com.pirateman.exercisemanager.interval

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.FragmentIntervalContainerBinding
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import com.pirateman.exercisemanager.workout.WorkoutRepsSets
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class IntervalContainerFragment : Fragment() {

    val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_interval_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val intervalContainerBinding = FragmentIntervalContainerBinding.bind(view)
        intervalContainerBinding.btnSubmitWorkout.setOnClickListener {


            Navigation.findNavController(it).navigate(R.id.workoutsContainerFragment)


        }


        //TODO: Add existing intervals if there are any, else add once

//        val intervals = exerciseViewModel.intervals
//
//        if (intervals.size > 0) {
//
//            for ((key, interval) in intervals) {
//
//                val ifrag = IntervalFragment()
//
//                val b = Bundle()
//                b.putParcelable(key.name, interval[intervals.size - 1])
//                ifrag.arguments = b
//
//
//                val fragmentTransition = childFragmentManager.beginTransaction()
//
//                fragmentTransition.add(R.id.ll_IntervalContainer, ifrag)
//                        .commit()
//
//            }
//
//        } else {
//            //Add empty fragment
//            val fragmentTransition = childFragmentManager.beginTransaction()
//
//            fragmentTransition.add(R.id.ll_IntervalContainer, IntervalFragment())
//                    .commit()
//        }

        //Pass WorkoutRepsSets to IntervalFragment


//        imgAddExerciseInterval.setOnClickListener {
//            val fragmentTransition = childFragmentManager.beginTransaction()
//
//            fragmentTransition.add(R.id.ll_IntervalContainer, IntervalFragment())
//                    .commit()
//
//
//        }
    }

    //Allows the child fragment to add another fragment to itself
    fun addIntervalFragment(wrs: ArrayList<WorkoutRepsSets>?) {


        val intervals = arrayListOf<Interval>()
        wrs?.let{
            for (inte in wrs){
                intervals.add(Interval(inte.reps.toInt(), inte.sets.toInt(), inte.weight.toInt()))
            }
        }

        //Get Intervals from database, add data into fragment
        exerciseViewModel.addIntervals(exerciseViewModel.currentExerciseSelected, intervals)


        val fragmentTransition = childFragmentManager.beginTransaction()

        fragmentTransition.add(R.id.ll_IntervalContainer, IntervalFragment())
                .commit()
    }
}