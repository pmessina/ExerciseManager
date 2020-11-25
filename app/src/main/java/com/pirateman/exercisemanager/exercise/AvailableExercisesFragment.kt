package com.pirateman.exercisemanager.exercise

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.api.Distribution
import com.pirateman.exercisemanager.R
import kotlinx.android.synthetic.main.fragment_available_exercises.*
import org.koin.android.viewmodel.ext.android.viewModel

@RequiresApi(Build.VERSION_CODES.M)
class AvailableExercisesFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_available_exercises, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rvExerciseList.setHasFixedSize(true)

//        rvExerciseList.layoutManager = object : LinearLayoutManager(this.context){
//            override fun canScrollVertically(): Boolean {
//                return false
//            }
//        }

        rvExerciseList.layoutManager = LinearLayoutManager(this.context)


        val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        rvExerciseList.addItemDecoration(divider)

        if (exerciseViewModel.exerciseList.isEmpty()) {
            exerciseViewModel.getAllExercises().observe(this.viewLifecycleOwner, Observer { t ->
                exerciseViewModel.exerciseList.addAll(t)
                
                val exerciseAdapter = AvailableExercisesAdapter(t, this.requireContext())
                rvExerciseList.adapter = exerciseAdapter


                exerciseAdapter.notifyDataSetChanged()
                //Toast.makeText(this.context, "${t.size} exercises from database", Toast.LENGTH_LONG).show()
            })

        }
        else{
            val list = exerciseViewModel.exerciseList
            val exerciseAdapter = AvailableExercisesAdapter(list, this.requireContext())
            rvExerciseList.adapter = exerciseAdapter




            //Toast.makeText(this.context, "${list.size} exercises from viewmodel", Toast.LENGTH_LONG).show()
        }


        btnSubmitExercises.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.selectedExercisesFragment)
        }


        super.onViewCreated(view, savedInstanceState)
    }


}