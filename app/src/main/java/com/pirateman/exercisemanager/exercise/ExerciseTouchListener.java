package com.pirateman.exercisemanager.exercise;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.pirateman.exercisemanager.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
public class ExerciseTouchListener implements RecyclerView.OnItemTouchListener
{
    private ClickListener clickListener;
    private GestureDetector gestureDetector;
    private RecyclerView recyclerView;
    private Context context;

    public ExerciseTouchListener(final Context context, final RecyclerView recyclerView)
    {
        this.context = context;
        this.recyclerView = recyclerView;
        clickListener = new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                switch (view.getId())
                {
                    case R.id.tvMethod:
                    case R.id.tvMuscleGroup:
                    case R.id.tvName:
                        Toast.makeText(context, String.valueOf(view), Toast.LENGTH_SHORT).show();

                    case R.id.imgOptions:
                        break;
                    case R.id.cvExerciseItem:
                        CardView cv = view.findViewById(R.id.cvExerciseItem);
                        cv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(context, "Card View clicked", Toast.LENGTH_SHORT).show();
                            }
                        });

                }

                //Toast.makeText(context, v.getText().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        };


        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());

        if (child != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Toast.makeText(context, "Touch Item Event", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}
