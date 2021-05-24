package com.pirateman.exercisemanager.interval

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface IntervalDao {
    @get:Query("select * from interval")
    val all: LiveData<List<Interval?>?>?

    @Query("select * from interval where id = :id")
    fun getIntervalById(id: Int): LiveData<Interval?>?

    @Query("delete from interval WHERE id = :id")
    fun removeInterval(id: Int)

    @Update
    fun updateInterval(interval: Interval?)

    @Insert
    fun insertInterval(interval: Interval?)

    @Insert
    fun insertAll(vararg intervals: Interval?)

    @Query("delete from interval")
    fun removeAll()

    @get:Query("SELECT COUNT('id') FROM interval")
    val numberOfRows: Int

    @Query("select * from interval where exerciseId = :exerciseId")
    fun getIntervalsByExerciseId(exerciseId: Long): LiveData<List<Interval>>


}