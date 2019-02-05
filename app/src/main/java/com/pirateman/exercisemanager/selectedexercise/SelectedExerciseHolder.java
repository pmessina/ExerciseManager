package com.pirateman.exercisemanager.selectedexercise;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.pirateman.exercisemanager.databinding.SelectedExerciseItemBinding;
import com.pirateman.exercisemanager.exercise.BaseHolder;
import com.pirateman.exercisemanager.exercise.Exercise;
import com.pirateman.exercisemanager.interval.IntervalActivity;

public class SelectedExerciseHolder extends BaseHolder implements View.OnClickListener, View.OnLongClickListener
{
    private SelectedExerciseItemBinding binding;

    public SelectedExerciseHolder(SelectedExerciseItemBinding binding, final Context context)
    {
        super(binding, context);
        this.binding = binding;
        this.context = context;

        binding.getRoot().setOnClickListener(this);
        binding.getRoot().setOnLongClickListener(this);
    }

    public void bind(Exercise exercise)
    {
        binding.setExerciseRecord(exercise);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, IntervalActivity.class);
        intent.putExtra("exercise", binding.getExerciseRecord());
        context.startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(context, "Go to next activity", Toast.LENGTH_SHORT).show();

        return true;
    }
}
