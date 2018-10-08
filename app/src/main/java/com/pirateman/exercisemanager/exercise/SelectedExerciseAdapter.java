package com.pirateman.exercisemanager.exercise;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pirateman.exercisemanager.R;
import com.pirateman.exercisemanager.databinding.RecyclerViewExerciseItemBindingImpl;
import com.pirateman.exercisemanager.databinding.RecyclerViewSelectedExerciseItemBindingImpl;

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
        RecyclerViewSelectedExerciseItemBindingImpl binding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_selected_exercise_item, parent, false);

        return new SelectedExerciseHolder(context, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        Exercise item = exerciseList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
