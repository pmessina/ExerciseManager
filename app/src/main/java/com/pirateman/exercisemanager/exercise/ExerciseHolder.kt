package com.pirateman.exercisemanager.exercise

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ExerciseHolder(v: View) : RecyclerView.ViewHolder(v) {
    //, View.OnClickListener, OnLongClickListener, OnCreateContextMenuListener {
//    private val binding: SelectedExerciseItemBinding
//    private val listener: OnAddExerciseListener
//    private val deleteListener: OnDeleteExerciseListener

    val tvName = v.findViewById(R.id.tvName) as TextView



    //private val menu: PopupMenu? = null

    //Set Exercise in adapter
//    override fun bind(exercise: Exercise?) {
//
//    }

//    override fun onClick(view: View) {
//        view.setOnCreateContextMenuListener(this)
//        when (view.id) {
//        }
//                ExerciseDatabase database = ExerciseDatabase.getINSTANCE(context);
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
//    }

//    override fun onLongClick(view: View): Boolean {
//
//        TextView v = view.findViewById(R.id.exerciseId);
//        String str =  v.getText().toString();
//        int id = Integer.valueOf(str);
//        removeExercise(id);
//        return true
//    }

    fun removeExercise(id: Int) {
        val executor: Executor = Executors.newSingleThreadExecutor()
        //executor.execute(ExerciseRunnable(id))
        //deleteListener.deleteExercise(id)

//        ExerciseAdapter adapter = new ExerciseAdapter(context);
//        adapter.removeExercise(id);
    }

    fun showPopUp(view: View?) {
//        menu.show()
//        menu.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener() {
//            fun onMenuItemClick(menuItem: MenuItem): Boolean {
//                when (menuItem.getItemId()) {
//                    R.id.delete_item -> {
//                        //Delete from Database
//                        val id: Int = Integer.valueOf(binding.exerciseId.getText().toString())
//                        removeExercise(id)
//                        menu.dismiss()
//                        return true
//                    }
//                    R.id.edit_item -> {
//                        //                        String tvMethod = binding.tvMethod.getText().toString();
////                        String tvMuscleGroup = binding.tvMuscleGroup.getText().toString();
//                        val tvName: String = binding.tvName.getText().toString()
//                        val intent = Intent(context, ExerciseEditActivity::class.java)
//                        //                        intent.putExtra("tvMethod", tvMethod);
////                        intent.putExtra("tvMuscleGroup", tvMuscleGroup);
//                        intent.putExtra("tvName", tvName)
//                        context.startActivity(intent)
//                        menu.dismiss()
//                        return true
//                    }
//                }
//                return false
//            }
//        })
    }

//    override fun onCreateContextMenu(menu: ContextMenu, v: View?, menuInfo: ContextMenuInfo?) {
//        menu.setHeaderTitle("Exercise Options")
//        menu.add("Edit")
//        menu.add("Delete")
//    }

    //    @Override
    //    public boolean onContextClick(MenuItem item)
    //    {
    //        Toast.makeText(this, "Item Selected", Toast.LENGTH_SHORT).show();
    //
    //        return super.onContextItemSelected(item);
    //    }
//    internal inner class ExerciseRunnable(private val id: Int) : Runnable {
//        override fun run() {
//            val database = ExerciseDatabase.getINSTANCE(context)
//            database.exerciseDao().removeExercise(id)
//            //Log.i("RemoveExercise", "Remove successful")
//        }
//
//    }

    init {
//        this.binding = binding
        //        binding.cvExerciseItem.setOnClickListener(this);
//        binding.cvExerciseItem.setLongClickable(true);
//        binding.cvExerciseItem.setOnLongClickListener(this);
//
//        binding.imgOptions.setOnClickListener(this);
//        binding.imgOptions.setLongClickable(true);
//        binding.imgOptions.setOnLongClickListener(this);
//        listener = context
//        deleteListener = context
    }
}

//class ExerciseHolder(v: View) : SwappingHolder(v), View.OnClickListener, OnLongClickListener {
//    var exerciseViewModel: ExerciseViewModel? = null
//
//    //    public ExerciseHolder(ExerciseItemBinding binding) {
//    //        super(binding.getRoot(), multiSelector);
//    //        this.binding = binding;
//    //
//    //        binding.getRoot().setOnClickListener(this);
//    //        binding.getRoot().setOnLongClickListener(this);
//    //    }
//    //    public void bind(ExerciseViewModel exerciseViewModel, int position) {
//    //        binding.setVariable(BR.exerciseViewModel, exerciseViewModel);
//    //        binding.setVariable(BR.position, position);
//    //
//    ////        exerciseViewModel.getSelectedExercise(exercise.getId());
//    ////        binding.setExerciseRecord(exercise);
//    //    }
//    override fun onClick(view: View) {
//        ExerciseHolder.Companion.multiSelector.setSelected(this, true)
//        ExerciseHolder.Companion.multiSelector.setSelectable(true)
//        if (!ExerciseHolder.Companion.multiSelector.tapSelection(this)) {
////            ExerciseItemBinding binding = DataBindingUtil.getBinding(view);
////
//////            if (binding.getExerciseRecord() != null) {
//////                selectedExercises.add(binding.getExerciseRecord());
//////            }
////
////            if (binding.getExerciseRecord() != null) {
////                exerciseViewModel.setExercise(binding.getExerciseRecord());
////            }
//        }
//    }
//
//    override fun onLongClick(view: View): Boolean {
//        ExerciseHolder.Companion.multiSelector.setSelected(this, true)
//        ExerciseHolder.Companion.multiSelector.setSelectable(true)
//
//        //ExerciseItemBinding binding = DataBindingUtil.getBinding(view);
//
////        if (binding != null) {
////            if (binding.getExerciseRecord() != null) {
////                selectedExercises.add(binding.getExerciseRecord());
////            }
////        }
//
////        if (binding.getExerciseRecord() != null) {
////            exerciseViewModel.setSelectedExercise(binding.getExerciseRecord());
////        }
//        return true
//    } //    public void sendSelectedExercises()
//
//    //    {
//    //
//    //    }
//    companion object {
//        //private ExerciseItemBinding binding;
//        private val multiSelector = MultiSelector()
//    }
//}