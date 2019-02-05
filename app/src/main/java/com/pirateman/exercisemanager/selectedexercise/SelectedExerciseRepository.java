package com.pirateman.exercisemanager.selectedexercise;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.pirateman.exercisemanager.exercise.Exercise;
import com.pirateman.exercisemanager.exercise.ExerciseActivity;
import com.pirateman.exercisemanager.exercise.ExerciseDao;
import com.pirateman.exercisemanager.exercise.ExerciseDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SelectedExerciseRepository
{
    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> exercises;

    SelectedExerciseRepository(Application application)
    {
        ExerciseDatabase exerciseDatabase = ExerciseDatabase.getINSTANCE(application);
        exerciseDao = exerciseDatabase.exerciseDao();

        exercises = exerciseDao.getAll();
    }

    public LiveData<List<Exercise>> getAllExercises()
    {
        return exercises;
    }

    public void insertExercise(final Exercise exercise)
    {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> exerciseDao.insertExercise(exercise));

    }
}
