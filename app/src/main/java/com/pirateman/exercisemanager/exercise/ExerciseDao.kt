package com.pirateman.exercisemanager.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.pirateman.exercisemanager.interval.Interval

@Dao
interface ExerciseDao {
    @Query("select * from exercise where name = :name")
    fun getExerciseByName(name: String?): LiveData<Exercise?>?

    @Query("select * from exercise")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    fun insertExercise(exercise: Exercise?): Long

    @Query("select * from exercise where id = :id")
    fun getExerciseById(id: Int): LiveData<Exercise?>?

    @Query("delete from exercise WHERE id = :id")
    fun removeExercise(id: Int)

    @Update
    suspend fun updateExercise(exercise:Exercise)


    @Insert
    fun insertIntervals(interval: List<Interval>)

    @Insert
    fun insertAll(vararg exercises: Exercise?)



//    @Query("delete from exercise")
//    fun removeAll()

    @Delete
    fun removeAll(exercise: Exercise)

    @get:Query("SELECT COUNT('id') FROM exercise")
    val numberOfRows: Int

}