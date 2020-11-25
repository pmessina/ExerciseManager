package com.pirateman.exercisemanager.exercise

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pirateman.exercisemanager.FirebaseWorker
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

    private lateinit var auth: FirebaseAuth

    private val exerciseViewModel: ExerciseViewModel by viewModel()

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        auth = Firebase.auth

        firebaseSignIn()

        val fireBaseRequest = OneTimeWorkRequestBuilder<FirebaseWorker>().build()

        WorkManager.getInstance(this).enqueue(fireBaseRequest)

        val nav = findNavController(nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, nav, AppBarConfiguration(nav.graph))


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

    private fun firebaseSignIn() {
        // Initiate sign in with custom token
        // [START sign_in_custom]
        auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInAnonymously:success")
                        //val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInAnonymously:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }
                }

        // [END sign_in_custom]
    }

    companion object {
        private const val TAG = "ExerciseHomeActivity"
    }
}