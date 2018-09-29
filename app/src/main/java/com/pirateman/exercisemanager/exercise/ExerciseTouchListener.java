package com.pirateman.exercisemanager.exercise;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
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

                ImageButton v = view.findViewById(R.id.imgOptions);

                if (v.hasOnClickListeners()) return;

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showPopUp(view);
                    }
                });

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
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());


        if (child != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child));
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

    public void showPopUp(View view)
    {
        PopupMenu menu = new PopupMenu(context, view, Gravity.END, 0, R.style.MyPopupMenu);

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.delete_item:
                        //Delete from Database
                        Toast.makeText(context, "To be deleted", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });
        menu.inflate(R.menu.menu_options_exercise_row);
        menu.show();
    }

    static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

}
