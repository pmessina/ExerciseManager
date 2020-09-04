package com.pirateman.exercisemanager.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.fragment_available_exercises.*
import org.koin.android.viewmodel.ext.android.viewModel


class AvailableExercisesFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_available_exercises, container, false)





//        NavigationUI.setupWithNavController()
//        NavigationUI.setupWithNavController(tbAvailableExercises, nav)


//        rvSelectedExerciseList.setHasFixedSize(true)
//        rvSelectedExerciseList.layoutManager = LinearLayoutManager(this.context)


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //val rv = view.findViewById(R.id.rvExerciseList) as RecyclerView
        rvExerciseList.setHasFixedSize(true)
        rvExerciseList.layoutManager = LinearLayoutManager(this.context)

        exerciseViewModel.getAllExercises().observe(this.viewLifecycleOwner, Observer { t ->
            rvExerciseList.adapter = ExerciseAdapter(t)
            Toast.makeText(this.context, "${t.size} exercises", Toast.LENGTH_LONG).show()

        })

        btnAddExercise.setOnClickListener { NavHostFragment.findNavController(this).navigate(R.id.addExerciseFragment) }


        super.onViewCreated(view, savedInstanceState)
    }


}