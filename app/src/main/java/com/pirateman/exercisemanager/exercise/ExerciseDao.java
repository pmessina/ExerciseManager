package com.pirateman.exercisemanager.exercise;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ExerciseDao
{
    @Query("select * from exercise where name = :name")
    LiveData<Exercise> getExerciseByName(String name);

    @Query("select * from exercise")
    LiveData<List<Exercise>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExercise(Exercise exercise);

    @Query("select * from exercise where id = :id")
    LiveData<Exercise> getExerciseById(int id);

    @Delete
    void removeExercise(Exercise exercise);

    @Insert
    void insertInterval(Interval interval);

}
