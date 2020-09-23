package com.pirateman.exercisemanager.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pirateman.exercisemanager.R
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class ExerciseFragment : Fragment() {

    val exerciseViewModel: ExerciseViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        exerciseViewModel.getAllExercises().observe(this.viewLifecycleOwner, Observer {

            for (i in it){
                Toast.makeText(this.context, i.name, Toast.LENGTH_SHORT).show()
            }
        })
        return inflater.inflate(R.layout.recycler_view_exercise_item, container, false)
    }
}