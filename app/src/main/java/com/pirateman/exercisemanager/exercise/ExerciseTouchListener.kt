package com.pirateman.exercisemanager.exercise

import android.content.Context
import android.os.Build
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
class ExerciseTouchListener(private val context: Context, private val recyclerView: RecyclerView) : OnItemTouchListener {
    private val clickListener: ClickListener
    private val gestureDetector: GestureDetector
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val child = rv.findChildViewUnder(e.x, e.y)
        if (child != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child))
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        Toast.makeText(context, "Touch Item Event", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    internal interface ClickListener {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View?, position: Int)
    }

    init {
        clickListener = object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (view.id) {
                }

                //Toast.makeText(context, v.getText().toString(), Toast.LENGTH_LONG).show();
            }

            override fun onLongClick(view: View?, position: Int) {}
        }
        gestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child = recyclerView.findChildViewUnder(e.x, e.y)
                if (child != null) {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        })
    }
}