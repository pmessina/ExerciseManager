package com.pirateman.exercisemanager.selectedexercise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pirateman.exercisemanager.databinding.SelectedExerciseItemBinding;
import com.pirateman.exercisemanager.exercise.Exercise;

import java.util.ArrayList;
import java.util.List;

public class SelectedExerciseAdapter extends RecyclerView.Adapter<SelectedExerciseHolder>
{
    private Context context;

    private List<Exercise> exerciseList;

    public SelectedExerciseAdapter(Context context)
    {
        exerciseList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public SelectedExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SelectedExerciseItemBinding binding = SelectedExerciseItemBinding.inflate(inflater, parent, false);

        return new SelectedExerciseHolder(binding, context);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedExerciseHolder holder, int position) {
        Exercise item = getExerciseList().get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return getExerciseList().size();
    }

    public List<Exercise> getExerciseList()
    {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList)
    {
        this.exerciseList = exerciseList;
        notifyDataSetChanged();
    }

    public void setExercise(Exercise exercise)
    {
        exerciseList.add(exercise);
        notifyDataSetChanged();
    }
}
