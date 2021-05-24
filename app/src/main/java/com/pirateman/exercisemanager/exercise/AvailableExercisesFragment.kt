package com.pirateman.exercisemanager.exercise

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.impl.model.Preference
import com.pirateman.exercisemanager.R
import com.pirateman.exercisemanager.databinding.FragmentAvailableExercisesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.prefs.Preferences

@RequiresApi(Build.VERSION_CODES.M)
class AvailableExercisesFragment : Fragment() {

    private val exerciseViewModel: ExerciseViewModel by viewModel()

//    val Context.mydatastore: DataStore<Preferences> by preferencesDataStore(name="settings")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAvailableExercisesBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val binding = FragmentAvailableExercisesBinding.bind(view)

        //Get all exercises once, populate adapter
        //If there is a database change, we don't need to observe the list

        exerciseViewModel.getAllExercises().value

        exerciseViewModel.getAllExercises().observe(this.viewLifecycleOwner, { t ->

            Log.i("AvailableExercisesFragment", "${t.size} exercises from database")

//            if (exerciseViewModel.exerciseList.isEmpty()){
                exerciseViewModel.exerciseList = t as ArrayList<Exercise>

            val exerciseAdapter = AvailableExercisesAdapter(exerciseViewModel, view.context)
            binding.rvExerciseList.adapter = exerciseAdapter
            binding.rvExerciseList.setHasFixedSize(true)
            binding.rvExerciseList.layoutManager = LinearLayoutManager(view.context)

            //}
//            else{
//                exerciseViewModel.exerciseList.clear()
//            }
            //exerciseViewModel.exerciseList = t as ArrayList<Exercise>

            //exerciseAdapter.notifyDataSetChanged()



        })


        //Toast.makeText(this.context, "${exerciseViewModel.exerciseList.size} exercises from database", Toast.LENGTH_SHORT).show()
//        val exerciseAdapter = AvailableExercisesAdapter(exerciseViewModel, view.context)
//        binding.rvExerciseList.adapter = exerciseAdapter
//        binding.rvExerciseList.setHasFixedSize(true)
//        binding.rvExerciseList.layoutManager = LinearLayoutManager(view.context)

        val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        binding.rvExerciseList.addItemDecoration(divider)

        binding.btnSubmitAvailableExercises.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.selectedExercisesFragment)
        }


        super.onViewCreated(view, savedInstanceState)
    }


    override fun onPause() {
        super.onPause()
        exerciseViewModel.getAllExercises().removeObservers(viewLifecycleOwner)
        //exerciseViewModel.getAllExercises().removeObservers(this)
    }


}