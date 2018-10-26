package com.pirateman.exercisemanager.exercise;

import android.content.Context;

import com.pirateman.exercisemanager.databinding.SelectedExerciseItemBinding;

public class SelectedExerciseHolder extends BaseHolder
{
    private SelectedExerciseItemBinding binding;

    public SelectedExerciseHolder(final Context context, SelectedExerciseItemBinding binding)
    {
        super(context, binding);
        this.binding = binding;
        this.context = context;
    }

    public void bind(Exercise exercise)
    {
        binding.setExerciseRecord(exercise);
    }

}
