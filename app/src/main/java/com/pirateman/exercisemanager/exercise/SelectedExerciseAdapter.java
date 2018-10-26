package com.pirateman.exercisemanager.exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pirateman.exercisemanager.databinding.SelectedExerciseItemBinding;

import androidx.annotation.NonNull;

public class SelectedExerciseAdapter extends ExerciseAdapter
{
    public SelectedExerciseAdapter(Context context)
    {
        super(context);
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SelectedExerciseItemBinding binding = SelectedExerciseItemBinding.inflate(inflater, parent, false);

        return new SelectedExerciseHolder(context, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        Exercise item = getExerciseList().get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return getExerciseList().size();
    }
}
