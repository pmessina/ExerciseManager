package com.pirateman.exercisemanager.selectedexercise;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pirateman.exercisemanager.exercise.Exercise;
import com.pirateman.exercisemanager.exercise.ExerciseDatabase;

import java.util.List;

public class SelectedExerciseViewModel extends AndroidViewModel
{
    SelectedExerciseRepository selectedExerciseRepository;
    public final LiveData<List<Exercise>> exerciseLiveData;


    public SelectedExerciseViewModel(Application application)
    {
        super(application);
        selectedExerciseRepository = new SelectedExerciseRepository(application);
        exerciseLiveData = selectedExerciseRepository.getAllExercises();
    }

    public LiveData<List<Exercise>> getExercises()
    {
        return exerciseLiveData;
    }

    public void insertExercise(Exercise exercise)
    {
        selectedExerciseRepository.insertExercise(exercise);
    }
}
