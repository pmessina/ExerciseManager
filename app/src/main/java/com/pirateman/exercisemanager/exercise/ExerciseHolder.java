package com.pirateman.exercisemanager.exercise;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
import com.pirateman.exercisemanager.BR;
import com.pirateman.exercisemanager.R;
import com.pirateman.exercisemanager.databinding.ExerciseItemBinding;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//public class ExerciseHolder extends BaseHolder implements View.OnClickListener,
//        View.OnLongClickListener, View.OnCreateContextMenuListener
//{
//    private SelectedExerciseItemBinding binding;
//
//    private OnAddExerciseListener listener;
//    private OnDeleteExerciseListener deleteListener;
//    private PopupMenu menu;
//
//
//    public ExerciseHolder(Context context, SelectedExerciseItemBinding binding)
//    {
//        super(context, binding);
//        this.binding = binding;
////        binding.cvExerciseItem.setOnClickListener(this);
////        binding.cvExerciseItem.setLongClickable(true);
////        binding.cvExerciseItem.setOnLongClickListener(this);
////
////        binding.imgOptions.setOnClickListener(this);
////        binding.imgOptions.setLongClickable(true);
////        binding.imgOptions.setOnLongClickListener(this);
//
//        listener = (OnAddExerciseListener) context;
//        deleteListener = (OnDeleteExerciseListener) context;
//    }
//
//
//    //Set Exercise in adapter
//    public void bind(Exercise exercise) {
//
//        binding.setExerciseRecord(exercise);
//    }
//
//    @Override
//    public void onClick(View view)
//    {
//        view.setOnCreateContextMenuListener(this);
//
//        switch(view.getId())
//        {
////            case R.id.imgOptions:
////
////                if (menu == null)
////                {
////                    menu = new PopupMenu(context, view, Gravity.END, 0, R.style.MyPopupMenu);
////                    menu.inflate(R.menu.menu_options_exercise_row);
////                }
////                showPopUp(view);
//////
////
////
////                break;
////            case R.id.cvExerciseItem:
////                listener.AddExercise(binding.getExerciseRecord());
////                //Insert exercise into selected exercises
////                break;
//        }
////        ExerciseDatabase database = ExerciseDatabase.getINSTANCE(context);
////
////        TextView v = view.findViewById(R.id.exerciseId);
////        String str =  v.getText().toString();
////
////        int id = Integer.valueOf(str);
////
////        LiveData<Exercise> exercise = database.exerciseDao().getExerciseById(id);
////
////        exercise.observe((AppCompatActivity) context, new Observer<Exercise>() {
////            @Override
////            public void onChanged(@Nullable Exercise exercise) {
////
////                listener.AddExercise(exercise);
////                Toast.makeText(context, exercise != null ? exercise.toString() : "Exercise is null", Toast.LENGTH_SHORT).show();
////            }
////        });
//
//    }
//
//    @Override
//    public boolean onLongClick(View view) {
//
////        TextView v = view.findViewById(R.id.exerciseId);
////        String str =  v.getText().toString();
////        int id = Integer.valueOf(str);
////        removeExercise(id);
//
//        return true;
//    }
//
//    public void removeExercise(int id)
//    {
//        Executor executor = Executors.newSingleThreadExecutor();
//        executor.execute(new ExerciseRunnable(id));
//
//        deleteListener.deleteExercise(id);
//
////        ExerciseAdapter adapter = new ExerciseAdapter(context);
////        adapter.removeExercise(id);
//    }
//
//
//
//    public void showPopUp(View view)
//    {
//        menu.show();
//
//        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                switch (menuItem.getItemId())
//                {
//                    case R.id.delete_item:
//                        //Delete from Database
//
//                        int id = Integer.valueOf(binding.exerciseId.getText().toString());
//                        removeExercise(id);
//
//                        menu.dismiss();
//                        return true;
//                    case R.id.edit_item:
////                        String tvMethod = binding.tvMethod.getText().toString();
////                        String tvMuscleGroup = binding.tvMuscleGroup.getText().toString();
//                        String tvName = binding.tvName.getText().toString();
//
//                        Intent intent = new Intent(context, ExerciseEditActivity.class);
////                        intent.putExtra("tvMethod", tvMethod);
////                        intent.putExtra("tvMuscleGroup", tvMuscleGroup);
//                        intent.putExtra("tvName", tvName);
//
//                        context.startActivity(intent);
//
//                        menu.dismiss();
//                        return true;
//                }
//
//
//
//                return false;
//            }
//        });
//
//
//
//    }
//
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
//    {
//        menu.setHeaderTitle("Exercise Options");
//        menu.add("Edit");
//        menu.add("Delete");
//    }
//
//
//
////    @Override
////    public boolean onContextClick(MenuItem item)
////    {
////        Toast.makeText(this, "Item Selected", Toast.LENGTH_SHORT).show();
////
////        return super.onContextItemSelected(item);
////    }
//
//
//    class ExerciseRunnable implements Runnable
//    {
//        private int id;
//
//        public ExerciseRunnable(int id)
//        {
//            this.id = id;
//        }
//        @Override
//        public void run() {
//            ExerciseDatabase database = ExerciseDatabase.getINSTANCE(context);
//            database.exerciseDao().removeExercise(id);
//
//            Log.i("RemoveExercise", "Remove successful");
//        }
//    }
//
//
//}


public class ExerciseHolder extends SwappingHolder implements View.OnClickListener, View.OnLongClickListener
 {
    private ExerciseItemBinding binding;
    private static final MultiSelector multiSelector = new MultiSelector();
    ExerciseViewModel exerciseViewModel;

    public ExerciseHolder(ExerciseItemBinding binding) {
        super(binding.getRoot(), multiSelector);
        this.binding = binding;

        binding.getRoot().setOnClickListener(this);
        binding.getRoot().setOnLongClickListener(this);
    }

    public void bind(ExerciseViewModel exerciseViewModel, int position) {
        binding.setVariable(BR.exerciseViewModel, exerciseViewModel);
        binding.setVariable(BR.position, position);

//        exerciseViewModel.getSelectedExercise(exercise.getId());
//        binding.setExerciseRecord(exercise);
    }

    @Override
    public void onClick(View view) {
        multiSelector.setSelected(this, true);
        multiSelector.setSelectable(true);

        if (!multiSelector.tapSelection(this)){
            ExerciseItemBinding binding = DataBindingUtil.getBinding(view);

//            if (binding.getExerciseRecord() != null) {
//                selectedExercises.add(binding.getExerciseRecord());
//            }

            if (binding.getExerciseRecord() != null) {
                exerciseViewModel.setExercise(binding.getExerciseRecord());
            }


        }
    }

    @Override
    public boolean onLongClick(View view) {

        multiSelector.setSelected(this, true);
        multiSelector.setSelectable(true);

        ExerciseItemBinding binding = DataBindingUtil.getBinding(view);

//        if (binding != null) {
//            if (binding.getExerciseRecord() != null) {
//                selectedExercises.add(binding.getExerciseRecord());
//            }
//        }

        if (binding.getExerciseRecord() != null) {
            exerciseViewModel.setSelectedExercise(binding.getExerciseRecord());
        }

        return true;
    }

//    public void sendSelectedExercises()
//    {
//
//    }
}