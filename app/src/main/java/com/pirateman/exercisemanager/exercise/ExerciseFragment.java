package com.pirateman.exercisemanager.exercise;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pirateman.exercisemanager.R;
import com.pirateman.exercisemanager.databinding.ExerciseItemBinding;


public class ExerciseFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ExerciseItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_exercise_item, container, false);

        Exercise exercise = new Exercise();
        exercise.setName("Chest Press");
        exercise.setMuscleGroup("Chest");
        exercise.setMethod("Barbells");

        binding.setExerciseRecord(exercise);

        return binding.getRoot();
    }
}
