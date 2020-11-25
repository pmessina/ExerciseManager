package com.pirateman.exercisemanager.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pirateman.exercisemanager.R
import kotlinx.android.synthetic.main.fragment_add_exercise.*


class AddExerciseFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_add_exercise, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSubmitNewExercise.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.availableExercisesFragment)
        }
    }
}