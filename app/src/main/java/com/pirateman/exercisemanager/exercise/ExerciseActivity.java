package com.pirateman.exercisemanager.exercise;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


//@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
public class ExerciseActivity extends AppCompatActivity implements
        OnAddExerciseListener, OnDeleteExerciseListener, OnUpdateExerciseListener, View.OnClickListener
{
    private RecyclerView recyclerView;
    //private RecyclerView selectedExercisesView;

    private ExerciseDao dao;

    private ExerciseAdapter exerciseAdapter;
    //private ExerciseAdapter selectedExercisesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dao = ExerciseDatabase.getINSTANCE(ExerciseActivity.this).exerciseDao();

        recyclerView = findViewById(R.id.rvExerciseList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //TODO: Implement click listener for button and recyclerview row
        //recyclerView.addOnItemTouchListener(new ExerciseTouchListener(this, recyclerView));

//        selectedExercisesView = findViewById(R.id.rvSelectedExercises);
//        selectedExercisesView.setHasFixedSize(true);
//        selectedExercisesView.setLayoutManager(new LinearLayoutManager(this));

        exerciseAdapter = new ExerciseAdapter(this);

        //selectedExercisesAdapter = new SelectedExerciseAdapter(this);

        setUpExerciseObservable();

        //clearTable();



//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(final View view)
//            {
//
//                Observable<Exercise> insert = Observable.fromCallable(new Callable<Exercise>()
//                {
//                    @Override
//                    public Exercise call() throws Exception
//                    {
//                        return insertExercise();
//                    }
//                });
//                insert.subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<Exercise>()
//                        {
//                            @Override
//                            public void onSubscribe(Disposable d)
//                            {
//                            }
//
//                            @Override
//                            public void onNext(Exercise s)
//                            {
//                                Snackbar.make(view, s.toString(), Snackbar.LENGTH_LONG)
//                                        .setAction("Action", null).show();
//                                exerciseAdapter.setExercise(s);
//                            }
//
//                            @Override
//                            public void onError(Throwable e)
//                            {
//                                Log.e("InsertExercise", e.getMessage());
//                            }
//
//                            @Override
//                            public void onComplete()
//                            {
//
//                            }
//                        });
//            }
//        });
    }

    private void clearTable()
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                ExerciseDatabase.getINSTANCE(ExerciseActivity.this).clearAllTables();
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("clearAllTables", "--- clearAllTables(): run() ---");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("clearAllTables", "--- clearAllTables(): accept(Throwable throwable) ----");
                        Log.d("throwableMessage", "throwable.getMessage(): "+throwable.getMessage());


                    }
                });
    }

    public Exercise insertExercise()
    {
        //  ExerciseDao dao = ExerciseDatabase.getINSTANCE(ExerciseActivity.this).exerciseDao();
        Exercise e1 = new Exercise();
        e1.setMethod("Barbells");
        e1.setMuscleGroup("Quads");
        e1.setName("Squats");

        dao.insertExercise(e1);

        return e1;
    }

    public void testDataBindingContentView()
    {
        //ExerciseItemBinding binding = DataBindingUtil.setContentView(this, R.layout.recycler_view_exercise_item);

        Exercise exercise = new Exercise();
        exercise.setName("Chest Press");
        exercise.setMuscleGroup("Chest");
        exercise.setMethod("Barbells");

        //binding.setExerciseRecord(exercise);
    }

    public void testDataBindingInflater()
    {
        //LinearLayout layout = findViewById(R.id.llExerciseList);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ExerciseFragment fragment = new ExerciseFragment();

        //ft.replace(R.id.llExerciseList, fragment);
        ft.commit();
    }

    public List<Exercise> testExerciseData()
    {
        return Arrays.asList(
                new Exercise("pull ups", "shoulders", "pull up bar"),
                new Exercise("push ups", "shoulders", "bosu"),
                new Exercise("leg press", "quads", "machine"),
                new Exercise("leg curls", "hamstrings", "machine")
        );
    }

    public void setUpExerciseObservable()
    {
        LiveData<List<Exercise>> exercises = dao.getAll();

        exercises.observe(this, new android.arch.lifecycle.Observer<List<Exercise>>()
        {
            @Override
            public void onChanged(List<Exercise> exercises)
            {
                setUpExerciseAdapter(ExerciseActivity.this, exercises);
            }
        });

    }

    public void setUpExerciseAdapter(Context context, List<Exercise> exercises)
    {
        exerciseAdapter.setExerciseList(exercises);
        recyclerView.setAdapter(exerciseAdapter);
    }

    @Override
    public void AddExercise(Exercise exercise)
    {
        //selectedExercisesAdapter.setExercise(exercise);
        //selectedExercisesView.setAdapter(selectedExercisesAdapter);
    }

    @Override
    public void deleteExercise(int id)
    {
        exerciseAdapter.removeExercise(id);
        recyclerView.setAdapter(exerciseAdapter);
    }

    @Override
    public void updateExercise(Exercise exercise)
    {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new EditExerciseRunnable(exercise));

        exerciseAdapter.updateExercise(exercise.getId());
    }

    @Override
    public void onClick(View view)
    {
        ArrayList<Exercise> exercises = exerciseAdapter.getSelectedExercises();

        Intent intent = new Intent(this, SelectedExerciseActivity.class);
        intent.putExtra("exercises", exercises);
        startActivity(intent);

        //ArrayList<Exercise> selectedExercises = new ArrayList<>(selectedExercisesAdapter.getExerciseList());


//        switch (view.getId())
//        {
//            case R.id.btnSubmit:
//                Intent intent = new Intent(this, IntervalActivity.class);
//                Bundle data = new Bundle();
//                data.putSerializable("selectedExercises", selectedExercises);
//
//                startActivity(intent);
//                break;
//        }
    }

    class EditExerciseRunnable implements Runnable
    {
        private Exercise exercise;

        public EditExerciseRunnable(Exercise exercise)
        {
            this.exercise = exercise;
        }

        @Override
        public void run()
        {
            ExerciseDatabase database = ExerciseDatabase.getINSTANCE(ExerciseActivity.this);
            database.exerciseDao().updateExercise(exercise);

            Log.i("RemoveExercise", "Update successful");
        }
    }


}




