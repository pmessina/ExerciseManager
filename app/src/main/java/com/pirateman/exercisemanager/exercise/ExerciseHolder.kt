package com.pirateman.exercisemanager.exercise

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.multiselector.MultiSelector
import com.bignerdranch.android.multiselector.SwappingHolder
import com.pirateman.exercisemanager.R

import org.koin.core.KoinComponent
import org.koin.core.inject

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ExerciseHolder(val view: View) : KoinComponent, SwappingHolder(view), View.OnClickListener, View.OnLongClickListener {

    val tvName = view.findViewById(R.id.tvName) as TextView

    private val exerciseViewModel: ExerciseViewModel by inject()

    init {
        view.isClickable = true
        view.setOnClickListener(this)
        view.isLongClickable = true
        view.setOnLongClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(itemView: View) {

        val context = itemView.context

        val p = itemView.parent as View

        val rv = p.findViewById(R.id.rvExerciseList) as RecyclerView
        rv.layoutManager = object : LinearLayoutManager(itemView.context){
            override fun canScrollHorizontally(): Boolean {
                return false
            }

        }



        val v = itemView.tag as Exercise

        if (!this.isSelectable && !v.selected) {
            v.selected = true
            multiSelector.isSelectable = true
            this.isSelectable = true
            multiSelector.setSelected(this, true)

            val color = context.resources.getColor(R.color.colorPrimary, context.theme)
            val drawable = ColorDrawable(color)
            view.setBackgroundColor(color)
            selectionModeBackgroundDrawable = drawable

        } else {
            v.selected = false
            multiSelector.isSelectable = false
            this.isSelectable = false
            multiSelector.setSelected(this, false)

            val color = context.resources.getColor(R.color.white, context.theme)
            val drawable = ColorDrawable(color)
            view.setBackgroundColor(color)
            selectionModeBackgroundDrawable = drawable

        }

        exerciseViewModel.setSelectedExercise(v, this.isSelectable)
    }

    override fun onLongClick(view: View): Boolean {
        multiSelector.setSelected(this, true)
        multiSelector.isSelectable = true

        return true
    }

    companion object {

        val multiSelector = MultiSelector()
    }
}