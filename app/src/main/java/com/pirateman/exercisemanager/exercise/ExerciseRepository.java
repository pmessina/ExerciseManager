package com.pirateman.exercisemanager.exercise;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExerciseRepository {
    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> exercises;

    ExerciseRepository(Application application)
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

    public LiveData<Exercise> getExercise(int id) {
        return exerciseDao.getExerciseById(id);
    }
}
