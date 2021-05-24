package com.pirateman.exercisemanager.selectedexercise

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.FragmentSelectedExercisesBinding
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectedExercisesFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_selected_exercises, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val selectedExercisesBinding = FragmentSelectedExercisesBinding.bind(view)
        selectedExercisesBinding.rvSelectedExerciseList.setHasFixedSize(true)
        selectedExercisesBinding.rvSelectedExerciseList.layoutManager = LinearLayoutManager(this.context)
        val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        selectedExercisesBinding.rvSelectedExerciseList.addItemDecoration(divider)

        val selectedExercisesList = exerciseViewModel.getSelectedExercisesList()

//        exerciseViewModel.selectedExerciseList.postValue(selectedExercisesList)
//
//        exerciseViewModel.selectedExerciseList.observe(viewLifecycleOwner, Observer { sel ->
//            val selectedExerciseAdapter = SelectedExercisesAdapter(sel, this.requireContext(), exerciseViewModel)
//            rvSelectedExerciseList.adapter = selectedExerciseAdapter
//
//        })

        val selectedExerciseAdapter = SelectedExercisesAdapter(selectedExercisesList, this.requireContext(), exerciseViewModel)
        selectedExercisesBinding.rvSelectedExerciseList.adapter = selectedExerciseAdapter


        selectedExercisesBinding.btnSubmitSelectedExercises.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.workoutsContainerFragment)
        }
    }



}