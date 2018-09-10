package com.pirateman.exercisemanager.exercise;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.pirateman.exercisemanager.R;
import com.pirateman.exercisemanager.databinding.RecyclerViewExerciseItemBinding;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExerciseActivity extends AppCompatActivity {

    private RecyclerView view;
    private ExerciseAdapter exerciseAdapter;

    private ExerciseDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //testDataBindingInflater();

        dao = ExerciseDatabase.getINSTANCE(ExerciseActivity.this).exerciseDao();


        view = findViewById(R.id.rvExerciseList);

        exerciseAdapter = new ExerciseAdapter(this);

        setUpExerciseObservable();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                Observable<Exercise> insert = Observable.fromCallable(new Callable<Exercise>() {
                    @Override
                    public Exercise call() throws Exception {
                        return insertExercise();
                    }
                });
                insert
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Exercise>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Exercise s) {
                            Snackbar.make(view, s.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            exerciseAdapter.setExercise(s);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("InsertExercise", e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

            }
        });
    }

    public Exercise insertExercise() {
        //  ExerciseDao dao = ExerciseDatabase.getINSTANCE(ExerciseActivity.this).exerciseDao();
        Exercise e1 = new Exercise();
        e1.setMethod("Barbells");
        e1.setMuscleGroup("Quads");
        e1.setName("Squats");


        dao.insertExercise(e1);

        return e1;
    }

    public void testDataBindingContentView() {

        RecyclerViewExerciseItemBinding binding = DataBindingUtil.setContentView(this, R.layout.recycler_view_exercise_item);

        Exercise exercise = new Exercise();
        exercise.setName("Chest Press");
        exercise.setMuscleGroup("Chest");
        exercise.setMethod("Barbells");

        binding.setExerciseRecord(exercise);
    }

    public void testDataBindingInflater() {
        //LinearLayout layout = findViewById(R.id.llExerciseList);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ExerciseFragment fragment = new ExerciseFragment();

        //ft.replace(R.id.llExerciseList, fragment);
        ft.commit();


    }

    public List<Exercise> testExerciseData() {
        return Arrays.asList(
                new Exercise("pull ups", "shoulders", "pull up bar"),
                new Exercise("push ups", "shoulders", "bosu"),
                new Exercise("leg press", "quads", "machine"),
                new Exercise("leg curls", "hamstrings", "machine")
        );
    }

    public void setUpExerciseObservable() {

        LiveData<List<Exercise>> exercises = dao.getAll();
        exercises.observe(this, new android.arch.lifecycle.Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable List<Exercise> exercises) {
                setUpExerciseAdapter(ExerciseActivity.this, exercises);
            }
        });



//        Observable<List<Exercise>> exerciseObservable = Observable.fromCallable(new Callable<List<Exercise>>() {
//            @Override
//            public List<Exercise> call() {
//
//
//
//                try {
//                    exercises = dao.getAll();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//
//                return exercises;
//            }
//        });
//        exerciseObservable
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<Exercise>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(List<Exercise> exercise) {
//                        setUpExerciseAdapter(ExerciseActivity.this, exercise);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("ExerciseObservable", e.getMessage());
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });

    }

    public void setUpExerciseAdapter(Context context, List<Exercise> exercises) {
        ////        List<Exercise> exercises = new ArrayList<>();
////        exercises.add(exercise);

        view.setHasFixedSize(true);

        view.setLayoutManager(new LinearLayoutManager(context));
        //Log.i("ExerciseObservable", exercise.toString());
        //exerciseAdapter.setExerciseList(exercise);
        //for Testing
        exerciseAdapter.setExerciseList(exercises);
        view.setAdapter(exerciseAdapter);
    }


}
