package com.pirateman.exercisemanager.selectedexercise

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.exercise.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_available_exercises.*
import kotlinx.android.synthetic.main.fragment_selected_exercises.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.compat.ViewModelCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectedExercisesFragment : Fragment(), KoinComponent {

    private val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_selected_exercises, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvSelectedExerciseList.setHasFixedSize(true)
        rvSelectedExerciseList.layoutManager = LinearLayoutManager(this.context)
        val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        rvSelectedExerciseList.addItemDecoration(divider)

        val selectedExercisesList = exerciseViewModel.exerciseList.filter { t -> t.selected } as ArrayList

//        exerciseViewModel.selectedExerciseList.p=ostValue(selectedExercisesList)
//
//        exerciseViewModel.selectedExerciseList.observe(viewLifecycleOwner, Observer { sel ->
//            val selectedExerciseAdapter = SelectedExercisesAdapter(sel, this.requireContext(), exerciseViewModel)
//            rvSelectedExerciseList.adapter = selectedExerciseAdapter
//
//        })
        val selectedExerciseAdapter = SelectedExercisesAdapter(selectedExercisesList, this.requireContext(), exerciseViewModel)
        rvSelectedExerciseList.adapter = selectedExerciseAdapter
    }


}