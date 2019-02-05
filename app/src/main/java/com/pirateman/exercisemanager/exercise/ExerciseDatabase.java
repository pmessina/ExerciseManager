package com.pirateman.exercisemanager.exercise;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.pirateman.exercisemanager.interval.Interval;
import com.pirateman.exercisemanager.interval.IntervalDao;

import java.util.concurrent.Executors;

@Database(entities = {Exercise.class, Interval.class}, version = 3, exportSchema = false)
public abstract class ExerciseDatabase extends RoomDatabase {
    private static ExerciseDatabase INSTANCE;

    public abstract ExerciseDao exerciseDao();

    public abstract IntervalDao intervalDao();

    public static ExerciseDatabase getINSTANCE(final Context context) {


        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context, ExerciseDatabase.class, "exercise_db")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    //getINSTANCE(context).exerciseDao().removeAll();

                                }
                            });
                        }

                    })
//                    .addMigrations(new Migration(1, 2) {
//                        @Override
//                        public void migrate(@NonNull SupportSQLiteDatabase database) {
//                            database.execSQL("delete from exercise");
//                        }
//                    })
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

            INSTANCE.populateData();

        }
        return INSTANCE;
    }

    public void populateData() {
        int rows = exerciseDao().getNumberOfRows();

        if (rows == 0) {
            this.beginTransaction();

            Exercise[] exercises = new Exercise[]{
                    new Exercise("Bicep Curls", "Biceps", "Barbells"),
                    new Exercise("Tricep Extension", "Triceps", "Barbells"),
                    new Exercise("Skull Crushers", "Triceps", "Barbells"),
                    new Exercise("Leg Press", "Quadriceps", "Machine"),
                    new Exercise("Back Extension", "Back", "Barbells"),
                    new Exercise("Shoulder Press", "Shoulders", "Barbells"),
                    new Exercise("Bent over Row", "Shoulders", "Barbells"),
                    new Exercise("Front Raises", "Shoulders", "Barbells"),
                    new Exercise("Lateral Raises", "Shoulders", "Barbells"),
                    new Exercise("Reverse Bridge Dips", "Triceps", "Weightless"),
                    new Exercise("Pushups", "Chest", "Weightless"),
                    new Exercise("Chest Fly", "Chest", "Barbells"),
                    new Exercise("One Arm Pushup Left/Right", "Abs", "Weightless"),
                    new Exercise("Hi Lo Plank", "Abs", "Weightless"),
                    new Exercise("High Side Planks Left/Right", "Arms", "Weightless"),
                    new Exercise("Reverse Plank Dips", "Triceps", "Weightless"),
            };

            exerciseDao().insertAll(exercises);
            setTransactionSuccessful();
            this.endTransaction();
        }
    }

}
