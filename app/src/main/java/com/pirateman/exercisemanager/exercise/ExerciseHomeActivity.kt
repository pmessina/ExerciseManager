package com.pirateman.exercisemanager.exercise

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.pirateman.exercisemanager.R
import org.koin.androidx.viewmodel.ext.android.viewModel

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
class ExerciseHomeActivity : AppCompatActivity() {
    //private val recyclerView: RecyclerView? = null

    //private RecyclerView selectedExercisesView;

    //private ExerciseAdapter selectedExercisesAdapter;

    //private lateinit var auth: FirebaseAuth

    private val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onStart() {
        super.onStart()

        //val currentUser = auth.currentUser

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

//        auth = Firebase.auth
//
//        firebaseSignIn()
//
        //Use if we need to make a write to Firebase
//        val fireBaseRequest = OneTimeWorkRequestBuilder<FirebaseWorker>().build()
//
//        WorkManager.getInstance(this).enqueue(fireBaseRequest)
        val nav = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = nav.navController

        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            AppBarConfiguration(navController.graph)
        )


    }

    override fun onSupportNavigateUp(): Boolean {
        val nav = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = nav.navController
        return navController.navigateUp(AppBarConfiguration(navController.graph))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_manage_exercises -> {
                val nav =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                val navController = nav.navController
                navController.navigate(R.id.addExerciseFragment)

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

    private fun firebaseSignIn() {
        // Initiate sign in with custom token
        // [START sign_in_custom]
//        auth.signInAnonymously()
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInAnonymously:success")
//                        //val user = auth.currentUser
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInAnonymously:failure", task.exception)
//                        Toast.makeText(baseContext, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show()
//
//                    }
//                }

        // [END sign_in_custom]
    }

    companion object {
        private const val TAG = "ExerciseHomeActivity"
    }
}