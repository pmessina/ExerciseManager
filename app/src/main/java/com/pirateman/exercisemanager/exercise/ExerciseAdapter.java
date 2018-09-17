package com.pirateman.exercisemanager.exercise;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
import com.pirateman.exercisemanager.R;
import com.pirateman.exercisemanager.databinding.RecyclerViewExerciseItemBindingImpl;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseHolder>{

    List<Exercise> exerciseList;

    private MultiSelector multiSelector;

    Context context;

    OnAddExerciseListener listener;


    public ExerciseAdapter(Context context) {
        exerciseList = new ArrayList<>();
        multiSelector = new MultiSelector();
        this.context = context;
        listener = (OnAddExerciseListener)context;
    }

    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void setExercise(Exercise exercise) {
        exerciseList.add(exercise);
        notifyDataSetChanged();
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerViewExerciseItemBindingImpl binding = DataBindingUtil.inflate(inflater, R.layout.recycler_view_exercise_item, parent, false);

        return new ExerciseHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder holder, int position) {
        Exercise item = exerciseList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    class ExerciseHolder extends SwappingHolder implements View.OnClickListener, View.OnLongClickListener{
        private RecyclerViewExerciseItemBindingImpl binding;

        public ExerciseHolder(RecyclerViewExerciseItemBindingImpl binding) {
            //super(binding.getRoot());
            super(binding.getRoot(), multiSelector);
            this.binding = binding;
            binding.getRoot().setLongClickable(true);
            binding.getRoot().setClickable(true);

            binding.getRoot().setOnClickListener(this);
            binding.getRoot().setOnLongClickListener(this);
        }

        public void bind(Exercise exercise) {
            binding.setExerciseRecord(exercise);
        }

        @Override
        public void onClick(View view) {
            if (!multiSelector.tapSelection(this)){
                //context.startActivity(new Intent(context, IntervalActivity.class));

            }
        }

        @Override
        public boolean onLongClick(View view) {

            if (!multiSelector.isSelectable())
            {
                multiSelector.setSelectable(true);
                multiSelector.setSelected(this, true);
                int position = this.getAdapterPosition();

                ExerciseDatabase database = ExerciseDatabase.getINSTANCE(context);
                //RecyclerViewExerciseItemBindingImpl binding = DataBindingUtil.findBinding(view);
//
//                LiveData<List<Exercise>> exercises = database.exerciseDao().getAll();
//
//                exercises.observe((AppCompatActivity) context, new Observer<List<Exercise>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Exercise> exercises) {
//                        for(Exercise e: exercises)
//                        {
//
//                        }
//                    }
//                });
                TextView v = view.findViewById(R.id.exerciseId);
                String str =  v.getText().toString();

                int id = Integer.valueOf(str);

                LiveData<Exercise> exercise = database.exerciseDao().getExerciseById(id);

                exercise.observe((AppCompatActivity) context, new Observer<Exercise>() {
                    @Override
                    public void onChanged(@Nullable Exercise exercise) {

                        listener.AddExercise(exercise);
                        Toast.makeText(context, exercise != null ? exercise.toString() : "Exercise is null", Toast.LENGTH_SHORT).show();
                    }
                });




                //Get Exercise value clicked


                return true;
            }

            Toast.makeText(context, "view not selectable", Toast.LENGTH_SHORT).show();


            return false;
        }
    }
}
