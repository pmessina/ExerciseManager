package com.pirateman.exercisemanager.exercise;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Exercise.class, Interval.class}, version = 3, exportSchema = false)
public abstract class ExerciseDatabase extends RoomDatabase
{
    private static ExerciseDatabase INSTANCE;

    public static ExerciseDatabase getINSTANCE(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room
                    .databaseBuilder(context, ExerciseDatabase.class, "exercise_db")
//                    .addMigrations(new Migration(1, 2) {
//                        @Override
//                        public void migrate(@NonNull SupportSQLiteDatabase database) {
//
//                        }
//                    })
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }

    public abstract ExerciseDao exerciseDao();
}
