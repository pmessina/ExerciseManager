package com.pirateman.exercisemanager.exercise

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder(v: View) : RecyclerView.ViewHolder(v) {

    //    public BaseHolder(ViewDataBinding binding, Context context)
    //    {
    //        super(binding.getRoot());
    //        this.context = context;
    //    }
    abstract fun bind(exercise: Exercise?)
}