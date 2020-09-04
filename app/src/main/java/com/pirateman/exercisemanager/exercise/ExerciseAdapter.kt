package com.pirateman.exercisemanager.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R
import java.util.*

class ExerciseAdapter(private val exerciseList: List<Exercise>) : RecyclerView.Adapter<ExerciseHolder>() {
//    private var selectedExercisePositions: ArrayList<Int>
//    private var holder: ExerciseHolder? = null
//    var exerciseViewModel: ExerciseViewModel? = null
//    private var exerciseList: List<Exercise>? = null
//    private var layoutId = 0

//    constructor(@LayoutRes layoutId: Int, exerciseViewModel: ExerciseViewModel?) {
//        this.layoutId = layoutId
//        selectedExercisePositions = ArrayList()
//        this.exerciseViewModel = exerciseViewModel
//    }

//    private fun getLayoutIdForPosition(position: Int): Int {
//        return layoutId
//    }

//    constructor(@LayoutRes layoutId: Int) {
//        selectedExercisePositions = ArrayList()
//    }

//    fun setExerciseList(exerciseList: List<Exercise>?) {
//        this.exerciseList = exerciseList
//        notifyDataSetChanged()
//    }

//    fun getExerciseList(): List<Exercise>? {
//        return exerciseList
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_exercise_item, parent, false) ?: parent.rootView

        return ExerciseHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        val exercise = exerciseList.get(position)
        holder.tvName.text = exercise.name

        //holder.bind(exerciseViewModel, item);
//        holder.bind(exerciseViewModel, position)
//        this.holder = holder
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

//    override fun getItemViewType(position: Int): Int {
//        return getLayoutIdForPosition(position)
//    }


}
