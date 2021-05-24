package com.pirateman.exercisemanager

import android.app.Application
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pirateman.exercisemanager.databinding.*
import com.pirateman.exercisemanager.exercise.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ExerciseApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ExerciseApplication)
            modules(listOf(exerciseModule, roomModule, firestoreModule))
        }
    }

    private val exerciseModule = module {
        //Create a view model only once
        single {
            ExerciseViewModel()
        }


        single {
            ExerciseRepository()
        }
    }

    private val roomModule = module {
        single {

            //val dbDelete = this@ExerciseApplication.baseContext.deleteDatabase("exercise_db")

//            if (dbDelete) {
//                Toast.makeText(baseContext, "Exercise Database deleted", Toast.LENGTH_SHORT).show()
//            }
            Room.databaseBuilder(get(), ExerciseDatabase::class.java, "exercise_db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        val request = OneTimeWorkRequestBuilder<ExerciseDatabaseWorker>().build()

                        WorkManager.getInstance(this@ExerciseApplication).enqueue(request)
                    }

                })


                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        }

        single {
            get<ExerciseDatabase>().exerciseDao()
        }
    }

    private val firestoreModule = module {
        single {
            Firebase.firestore
        }
    }
}