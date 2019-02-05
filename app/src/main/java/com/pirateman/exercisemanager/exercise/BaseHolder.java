package com.pirateman.exercisemanager.exercise;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public abstract class BaseHolder extends RecyclerView.ViewHolder
{
    protected Context context;

    public BaseHolder(ViewDataBinding binding, Context context)
    {
        super(binding.getRoot());
        this.context = context;
    }

    public abstract void bind(Exercise exercise);
}
