package com.pirateman.exercisemanager.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.FragmentAddExerciseBinding


class AddExerciseFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_add_exercise, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddExerciseBinding.inflate(layoutInflater)

        binding.btnSubmitNewExercise.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.availableExercisesFragment)
        }
    }
}