package com.pirateman.exercisemanager.exercise;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
import com.pirateman.exercisemanager.R;
import com.pirateman.exercisemanager.databinding.RecyclerViewExerciseItemBinding;
import com.pirateman.exercisemanager.databinding.RecyclerViewExerciseItemBindingImpl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//public class ExerciseHolder extends SwappingHolder implements View.OnClickListener, View.OnLongClickListener
// {
//    private RecyclerViewExerciseItemBindingImpl binding;
//    private static final MultiSelector multiSelector = new MultiSelector();
//
//    public ExerciseHolder(RecyclerViewExerciseItemBindingImpl binding) {
//        super(binding.getRoot(), multiSelector);
//        this.binding = binding;
//        binding.getRoot().setLongClickable(true);
//        binding.getRoot().setClickable(true);
//
//        binding.getRoot().setOnClickListener(this);
//        binding.getRoot().setOnLongClickListener(this);
//    }
//
//    public void bind(Exercise exercise) {
//        binding.setExerciseRecord(exercise);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (!multiSelector.tapSelection(this)){
//            //context.startActivity(new Intent(context, IntervalActivity.class));
//
//        }
//    }
//
//    @Override
//    public boolean onLongClick(View view) {
//
//        if (!multiSelector.isSelectable())
//        {
//            multiSelector.setSelectable(true);
//            multiSelector.setSelected(this, true);
//            int position = this.getAdapterPosition();
//
//            ExerciseDatabase database = ExerciseDatabase.getINSTANCE(context);
//
////
////                LiveData<List<Exercise>> exercises = database.exerciseDao().getAll();
////
////                exercises.observe((AppCompatActivity) context, new Observer<List<Exercise>>() {
////                    @Override
////                    public void onChanged(@Nullable List<Exercise> exercises) {
////                        for(Exercise e: exercises)
////                        {
////
////                        }
////                    }
////                });
//            TextView v = view.findViewById(R.id.exerciseId);
//            String str =  v.getText().toString();
//
//            int id = Integer.valueOf(str);
//
//            LiveData<Exercise> exercise = database.exerciseDao().getExerciseById(id);
//
//            exercise.observe((AppCompatActivity) context, new Observer<Exercise>() {
//                @Override
//                public void onChanged(@Nullable Exercise exercise) {
//
//                    listener.AddExercise(exercise);
//                    Toast.makeText(context, exercise != null ? exercise.toString() : "Exercise is null", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//
//
//            //Get Exercise value clicked
//
//
//            return true;
//        }
//
//        Toast.makeText(context, "view not selectable", Toast.LENGTH_SHORT).show();
//
//
//        return false;
//    }
//}
public class ExerciseHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, RecyclerView.OnItemTouchListener
{
    private RecyclerViewExerciseItemBinding binding;
    private Context context;

    private OnAddExerciseListener listener;
    private OnDeleteExerciseListener deleteListener;

    public ExerciseHolder(Context context, RecyclerViewExerciseItemBinding binding)
    {
        super(binding.getRoot());
        this.binding = binding;
        this.context = context;
        binding.getRoot().setClickable(true);
        binding.getRoot().setOnClickListener(this);
        binding.getRoot().setLongClickable(true);
        binding.getRoot().setOnLongClickListener(this);
        listener = (OnAddExerciseListener)context;
        deleteListener = (OnDeleteExerciseListener) context;
    }

    public void bind(Exercise exercise) {

        binding.setExerciseRecord(exercise);
    }

    //TODO:move to touchlistener
    @Override
    public void onClick(View view)
    {
//        ExerciseDatabase database = ExerciseDatabase.getINSTANCE(context);
//
//        TextView v = view.findViewById(R.id.exerciseId);
//        String str =  v.getText().toString();
//
//        int id = Integer.valueOf(str);
//
//        LiveData<Exercise> exercise = database.exerciseDao().getExerciseById(id);
//
//        exercise.observe((AppCompatActivity) context, new Observer<Exercise>() {
//            @Override
//            public void onChanged(@Nullable Exercise exercise) {
//
//                listener.AddExercise(exercise);
//                Toast.makeText(context, exercise != null ? exercise.toString() : "Exercise is null", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public boolean onLongClick(View view) {

        TextView v = view.findViewById(R.id.exerciseId);
        String str =  v.getText().toString();
        int id = Integer.valueOf(str);
        removeExercise(id);

        return true;
    }

    public void removeExercise(int id)
    {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new ExerciseRunnable(id));

        deleteListener.deleteExercise(id);

//        ExerciseAdapter adapter = new ExerciseAdapter(context);
//        adapter.removeExercise(id);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Toast.makeText(context, "Touch Event", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    class ExerciseRunnable implements Runnable
    {
        int id;

        public ExerciseRunnable(int id)
        {
            this.id = id;
        }
        @Override
        public void run() {
            ExerciseDatabase database = ExerciseDatabase.getINSTANCE(context);
            database.exerciseDao().removeExercise(id);

            Log.i("RemoveExercise", "Remove successful");
        }
    }
}