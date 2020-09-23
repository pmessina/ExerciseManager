package com.pirateman.exercisemanager.exercise

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.pirateman.exercisemanager.R
import kotlinx.android.synthetic.main.activity_exercise.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import java.util.*

//import com.pirateman.exercisemanager.selectedexercise.SelectedExerciseActivity;
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
class ExerciseHomeActivity : AppCompatActivity(), KoinComponent {
    //private val recyclerView: RecyclerView? = null

    //private RecyclerView selectedExercisesView;

    //private ExerciseAdapter selectedExercisesAdapter;


    private val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)



        val nav = findNavController(nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, nav, AppBarConfiguration(nav.graph))
//        NavigationUI.setupWithNavController(tbAvailableExercises, nav)

    }

    override fun onSupportNavigateUp(): Boolean {
        val nav = findNavController(nav_host_fragment)
        return nav.navigateUp(AppBarConfiguration(nav.graph))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_manage_exercises -> {
                val nhf = findNavController(nav_host_fragment)
                nhf.navigate(R.id.addExerciseFragment)

                true
            }
            //Do not change or onSupportNavigateUp will not get called
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_options_manage_exercises, menu)

        return true
    }

    //    private void clearTable()
    //    {
    //        Completable.fromAction(new Action() {
    //            @Override
    //            public void run() throws Exception {
    //                ExerciseDatabase.getINSTANCE(ExerciseActivity.this).clearAllTables();
    //            }
    //        }).subscribeOn(Schedulers.newThread())
    //                .subscribe(new Action() {
    //                    @Override
    //                    public void run() throws Exception {
    //                        Log.d("clearAllTables", "--- clearAllTables(): run() ---");
    //
    //                    }
    //                }, new Consumer<Throwable>() {
    //                    @Override
    //                    public void accept(Throwable throwable) throws Exception {
    //                        Log.d("clearAllTables", "--- clearAllTables(): accept(Throwable throwable) ----");
    //                        Log.d("throwableMessage", "throwable.getMessage(): "+throwable.getMessage());
    //
    //
    //                    }
    //                });
    //    }
    fun insertExercise(): Exercise {
        //  ExerciseDao dao = ExerciseDatabase.getINSTANCE(ExerciseActivity.this).exerciseDao();
        val e1 = Exercise("Barbells", "Quads", "Squats")

        //dao!!.insertExercise(e1)
        return e1
    }

    fun testDataBindingContentView() {
        //ExerciseItemBinding binding = DataBindingUtil.setContentView(this, R.layout.recycler_view_exercise_item);
//        val exercise = Exercise()
//        exercise.name = "Chest Press"
//        exercise.muscleGroup = "Chest"
//        exercise.method = "Barbells"

        //binding.setExerciseRecord(exercise);
    }

    fun testDataBindingInflater() {
        //LinearLayout layout = findViewById(R.id.llExerciseList);
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val fragment = ExerciseFragment()

        //ft.replace(R.id.llExerciseList, fragment);
        ft.commit()
    }

    fun testExerciseData(): List<Exercise> {
        return Arrays.asList(
                Exercise("pull ups", "shoulders", "pull up bar"),
                Exercise("push ups", "shoulders", "bosu"),
                Exercise("leg press", "quads", "machine"),
                Exercise("leg curls", "hamstrings", "machine")
        )
    }

    fun setUpExerciseObservable() {
//        LiveData<List<Exercise>> exercises = exerciseViewModel.getExercises();
//
//        exercises.observe(this, e -> {
//            exerciseViewModel.setUpExerciseAdapter(e);
//        });
    }

//    override fun AddExercise(exercise: Exercise) {
//        selectedExercisesAdapter.setExercise(exercise);
//        selectedExercisesView.setAdapter(selectedExercisesAdapter);
//    }
//
//    override fun deleteExercise(id: Int) {
//        exerciseAdapter.removeExercise(id);
//        recyclerView.setAdapter(exerciseAdapter);
//    }
//
//    override fun updateExercise(exercise: Exercise) {
//        Executor executor = Executors.newSingleThreadExecutor();
//        executor.execute(new EditExerciseRunnable(exercise));
//
//        exerciseAdapter.updateExercise(exercise.getId());
//    }

    // override fun onClick(view: View) {
    //List<Exercise> exercises = exerciseViewModel.getSelectedExercises();

//        Intent intent = new Intent(this, SelectedExerciseActivity.class);
//        intent.putExtra("exercises", new ArrayList<>(exercises));
//        startActivity(intent);

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
    //   }

    internal inner class EditExerciseRunnable(private val exercise: Exercise) : Runnable {
        override fun run() {
            //val database = ExerciseDatabase.getINSTANCE(this@ExerciseActivity)
            //database.exerciseDao().updateExercise(exercise)
            Log.i("RemoveExercise", "Update successful")
        }

    }
}