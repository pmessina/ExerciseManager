package com.pirateman.exercisemanager.exercise;

import android.content.Context;

import com.pirateman.exercisemanager.databinding.RecyclerViewSelectedExerciseItemBinding;
import com.pirateman.exercisemanager.databinding.RecyclerViewSelectedExerciseItemBindingImpl;

public class SelectedExerciseHolder extends BaseHolder
{
    private RecyclerViewSelectedExerciseItemBinding binding;

    public SelectedExerciseHolder(final Context context, RecyclerViewSelectedExerciseItemBindingImpl binding)
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
