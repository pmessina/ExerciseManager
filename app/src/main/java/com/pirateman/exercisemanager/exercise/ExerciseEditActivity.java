package com.pirateman.exercisemanager.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pirateman.exercisemanager.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExerciseEditActivity extends AppCompatActivity implements TextWatcher {

    private Exercise exercise;
    OnUpdateExerciseListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_edit);
        listener = (OnUpdateExerciseListener) this;

        Bundle data = getIntent().getExtras();

        String tvName = data.getString("tvName", "Name");
        String tvMethod = data.getString("tvMethod", "Method");
        String tvMuscleGroup = data.getString("tvMuscleGroup", "Muscle Group");

        final EditText edtName = findViewById(R.id.edtName);
        edtName.setText(tvName);
        final EditText edtMethod = findViewById(R.id.edtMethod);
        edtMethod.setText(tvMethod);
        final EditText edtMuscleGroup = findViewById(R.id.edtMuscleGroup);
        edtMuscleGroup.setText(tvMuscleGroup);

        edtName.addTextChangedListener(this);


        Button btnEditSubmit = findViewById(R.id.btnEditSubmit);

        btnEditSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.updateExercise(new Exercise(edtName.getText().toString(), edtMuscleGroup.getText().toString(), edtMethod.getText().toString()));
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
