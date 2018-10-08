package com.pirateman.exercisemanager.exercise;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pirateman.exercisemanager.R;
import com.pirateman.exercisemanager.databinding.RecyclerViewExerciseItemBindingImpl;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<BaseHolder>{

    protected List<Exercise> exerciseList;

    protected Context context;

    public ExerciseAdapter(Context context) {
        exerciseList = new ArrayList<>();
        this.context = context;
    }

    public ExerciseAdapter(Context context, @LayoutRes int layoutId) {
        exerciseList = new ArrayList<>();
        this.context = context;
    }

    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void setExercise(Exercise exercise)
    {
        exerciseList.add(exercise);
        notifyDataSetChanged();
    }

    public void updateExercise(int id)
    {
        notifyItemChanged(id);
    }

    public void removeExercise(int id)
    {
        exerciseList.remove(id);
        notifyDataSetChanged();
    }

    public void setExerciseList(List<Exercise> exerciseList)
    {
        this.exerciseList = exerciseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerViewExerciseItemBindingImpl binding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_exercise_item, parent, false);

        return new ExerciseHolder(context, binding);
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
