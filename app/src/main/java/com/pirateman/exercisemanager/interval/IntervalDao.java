package com.pirateman.exercisemanager.interval;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface IntervalDao {

    @Query("select * from interval")
    LiveData<List<Interval>> getAll();

    @Query("select * from interval where id = :id")
    LiveData<Interval> getIntervalById(int id);

    @Query("delete from interval WHERE id = :id")
    void removeInterval(int id);

    @Update
    void updateInterval(Interval interval);

    @Insert
    void insertInterval(Interval interval);

    @Insert
    void insertAll(Interval... intervals);

    @Query("delete from interval")
    void removeAll();

    @Query("SELECT COUNT('id') FROM interval")
    int getNumberOfRows();

    @Query("select * from interval where exerciseId = :exerciseId")
    LiveData<List<Interval>> getIntervalsByExerciseId(int exerciseId);

}
