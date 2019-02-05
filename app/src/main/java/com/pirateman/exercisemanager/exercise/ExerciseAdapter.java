package com.pirateman.exercisemanager.exercise;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pirateman.exercisemanager.databinding.ExerciseItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseHolder>
{

    private ArrayList<Integer> selectedExercisePositions;

    private ExerciseHolder holder;

    ExerciseViewModel exerciseViewModel;

    private List<Exercise> exerciseList;

    private int layoutId;

    public ExerciseAdapter(@LayoutRes int layoutId, ExerciseViewModel exerciseViewModel)
    {
        this.layoutId = layoutId;
        selectedExercisePositions = new ArrayList<>();
        this.exerciseViewModel = exerciseViewModel;
    }

    private int getLayoutIdForPosition(int position)
    {
        return layoutId;
    }

    public ExerciseAdapter(@LayoutRes int layoutId)
    {
        selectedExercisePositions = new ArrayList<>();
    }

    public void setExerciseList(List<Exercise> exerciseList)
    {
        this.exerciseList = exerciseList;
        notifyDataSetChanged();
    }

    public List<Exercise> getExerciseList()
    {
        return exerciseList;
    }

    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExerciseItemBinding binding = ExerciseItemBinding.inflate(inflater, parent, false);

        return new ExerciseHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder holder, int position)
    {
        //Exercise item = exerciseList.get(position);
        //holder.bind(exerciseViewModel, item);

        holder.bind(exerciseViewModel, position);
        this.holder = holder;
    }

    @Override
    public int getItemCount()
    {
        return exerciseList == null ? 0 : exerciseList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }
}
