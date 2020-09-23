package com.pirateman.exercisemanager

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pirateman.exercisemanager.exercise.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.concurrent.Executors

class ExerciseApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@ExerciseApplication)
            modules(listOf(exerciseModule, roomModule))
        }
    }

    private val exerciseModule = module{
        //Create a view model only once
        single{
            ExerciseViewModel()
        }


        factory {
            ExerciseRepository()
        }
    }

    private val roomModule = module{
        single{
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

        single{
            get<ExerciseDatabase>().exerciseDao()
        }
    }

    private val firestoreModule = module{
        single{
            Firebase.firestore
        }
    }
}