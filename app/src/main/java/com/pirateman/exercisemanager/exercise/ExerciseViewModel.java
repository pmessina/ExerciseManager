package com.pirateman.exercisemanager.exercise;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import com.pirateman.exercisemanager.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    ExerciseRepository exerciseRepository;
    public final LiveData<List<Exercise>> exerciseLiveData;

    private static List<Exercise> exerciseList;

    private ExerciseAdapter exerciseAdapter;

    private Application application;

    private static final ArrayList<Exercise> selectedExercises = new ArrayList<>();

    public ExerciseViewModel(Application application)
    {
        super(application);
        this.application = application;
        exerciseAdapter = new ExerciseAdapter(R.layout.content_exercise, this);
        exerciseRepository = new ExerciseRepository(application);
        exerciseLiveData = exerciseRepository.getAllExercises();
        exerciseList = new ArrayList<>();

    }

    public ExerciseAdapter getExerciseAdapter() {
        return exerciseAdapter;
    }

    public void setUpExerciseAdapter(List<Exercise> exercises)
    {

        exerciseAdapter.setExerciseList(exercises);
//        recyclerView.setAdapter(exerciseAdapter);
    }

    public List<Exercise> getExerciseList()
    {
        return exerciseList;
    }

    public void setExercise(Exercise exercise)
    {
        exerciseList.add(exercise);
        exerciseAdapter.notifyDataSetChanged();
    }

    public void setSelectedExercise(Exercise exercise)
    {
        selectedExercises.add(exercise);
        exerciseAdapter.notifyDataSetChanged();
    }

    public List<Exercise> getSelectedExercises()
    {
        return selectedExercises;
    }

    public void updateExercise(int id)
    {
        exerciseAdapter.notifyItemChanged(id);
    }

    public void removeExercise(int id)
    {
        exerciseList.remove(id);
        exerciseAdapter.notifyDataSetChanged();
    }

    public LiveData<List<Exercise>> getExercises()
    {
        return exerciseLiveData;
    }

    public void insertExercise(Exercise exercise)
    {
        exerciseRepository.insertExercise(exercise);
    }

    public LiveData<Exercise> getSelectedExercise(int id) {

         return exerciseRepository.getExercise(id);
    }


    public Exercise getSelectedExerciseAt(Integer id)
    {
        return exerciseAdapter.getExerciseList().get(id);
    }


}
