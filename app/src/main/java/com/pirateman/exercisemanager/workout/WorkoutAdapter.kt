package com.pirateman.exercisemanager.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pirateman.exercisemanager.R

class WorkoutAdapter : RecyclerView.Adapter<WorkoutViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_interval, parent, false)

        return WorkoutViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}

