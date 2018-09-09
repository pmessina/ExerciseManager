package com.pirateman.exercisemanager;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class ExerciseCriteriaActivity extends AppCompatActivity {

    //GridLayout layout;
    AutoCompleteTextView tvIncline;
    AutoCompleteTextView tvSpeed;
    AutoCompleteTextView tvDuration;
    AutoCompleteTextView tvIntervals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_criteria);

        //ViewDataBinding viewDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_grid_row);

        //layout = findViewById(R.id.gridLayout);

        Button btn = findViewById(R.id.btnAddInterval);

        tvDuration = findViewById(R.id.actvDuration);
        tvIncline = findViewById(R.id.actvIncline);
        tvSpeed = findViewById(R.id.actvSpeed);
        tvIntervals = findViewById(R.id.actvIntervals);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dur = tvDuration.getText().toString();
                String incl = tvIncline.getText().toString();
                String inte = tvIntervals.getText().toString();
                String spe = tvSpeed.getText().toString();

                Intent intent = new Intent(ExerciseCriteriaActivity.this, ExercisePlannerActivity.class);
                intent.putExtra("duration", dur);
                intent.putExtra("incline", incl);
                intent.putExtra("intervals", inte);
                intent.putExtra("speed", spe);

                startActivity(intent);

            }
        });

    }


    public void addRow()
    {
        AutoCompleteTextView textViewActivity = new AutoCompleteTextView(this);
        textViewActivity.setHint("Activity");
        textViewActivity.setTextSize(18f);
        AutoCompleteTextView textViewPace = new AutoCompleteTextView(this);
        textViewPace.setHint("Pace");
        textViewPace.setTextSize(18f);
        AutoCompleteTextView textViewIncline = new AutoCompleteTextView(this);
        textViewIncline.setHint("Incline");
        textViewIncline.setTextSize(18f);
        AutoCompleteTextView textViewDuration = new AutoCompleteTextView(this);
        textViewDuration.setHint("Duration");
        textViewDuration.setTextSize(18f);

//        layout.addView(textViewActivity, setGrid(2, 0));
//        layout.addView(textViewPace, setGrid(2, 1));
//        layout.addView(textViewIncline, setGrid(2, 2));
//        layout.addView(textViewDuration, setGrid(2, 3));

    }

    public GridLayout.LayoutParams setGrid(int row, int col)
    {
        GridLayout.Spec rowSpan = GridLayout.spec(row, 1);
        GridLayout.Spec colspan = GridLayout.spec(col, 1);

        GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(rowSpan, colspan);

        return gridParam;
    }
}
