package com.pirateman.exercisemanager.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pirateman.exercisemanager.interval.ExerciseWithIntervals
import com.pirateman.exercisemanager.interval.Interval
import com.pirateman.exercisemanager.workout.WorkoutRepsSets
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*
import kotlin.collections.HashMap

class ExerciseViewModel() : ViewModel(), KoinComponent {

    private val exerciseRepository: ExerciseRepository by inject()
    val exerciseAdapter: AvailableExercisesAdapter by inject()

    val currentInterval = Interval()

    var currentExerciseSelected: Exercise = Exercise("", "", "", false)

    var exerciseList = ArrayList<Exercise>()

    val intervals: HashMap<Exercise, ArrayList<WorkoutRepsSets>> = HashMap()

    val selectedExerciseList = MutableLiveData<ArrayList<Exercise>>()

    var currentExercise: Exercise = Exercise("", "", "", false)

//    val exercises: LiveData<List<Exercise>>

    //    public ExerciseAdapter getExerciseAdapter() {
    //        return exerciseAdapter;
    //    }
    fun setUpExerciseAdapter(exercises: List<Exercise?>?) {

        //exerciseAdapter.setExerciseList(exercises);
//        recyclerView.setAdapter(exerciseAdapter);
    }

    fun getAllExercises(): LiveData<List<Exercise>> {

        return exerciseRepository.getExercises()
    }

    fun setSelectedExercise(exercise: Exercise, selected: Boolean) {

        runBlocking {
            val result = async(Dispatchers.Default) {
                exercise.selected = selected
                exerciseRepository.updateExercise(exercise)
            }

            result.await()
        }
    }

    fun getSelectedExercisesList() = exerciseList.filter { t -> t.selected } as ArrayList

    fun getIntervalsByExercise(ex: Exercise): LiveData<List<Interval>> {
        return exerciseRepository.getIntervalsByExercise(ex)
    }


    fun addIntervals(ex: Exercise, intervals: ArrayList<Interval>) {
        val exerciseWithIntervals = ExerciseWithIntervals(ex, intervals)
        return exerciseRepository.insertIntervals(exerciseWithIntervals)
    }


//    fun setSelectedExercise(exercise: Exercise) {
//        selectedExercises.add(exercise)
//        //exerciseAdapter.notifyDataSetChanged();
//    }

    //val selectedExercises: List<Exercise>

//    fun updateExercise(exercise: Exercise) {
//        exerciseRepository.updateExercise(exercise)
//        //exerciseAdapter.notifyItemChanged(id)
//    }

    fun removeExercise(id: Int) {
        //exerciseList.removeAt(id)
        //exerciseAdapter.notifyDataSetChanged();
    }

    fun insertExercise(exercise: Exercise?) {
        //exerciseRepository.insertExercise(exercise)
    }

//    fun getSelectedExercise(id: Int): LiveData<Exercise> {
//        return exerciseRepository.getExercise(id)
//    }

    fun getSelectedExerciseAt(id: Int?): Exercise {
        return Exercise("", "", "") //exerciseAdapter.getExerciseList().get(id);
    }

    companion object {
        //private val exerciseList: MutableList<Exercise>
        //private val selectedExercises = ArrayList<Exercise>()
    }

//    init {
//        //exerciseAdapter = new ExerciseAdapter(R.layout.content_exercise, this);
//        //exerciseRepository = ExerciseRepository(application)
//       //exercises = exerciseRepository.allExercises
//        exerciseList = ArrayList()
//    }
}
