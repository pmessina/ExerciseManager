package com.pirateman.exercisemanager.exercise

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pirateman.exercisemanager.interval.Interval

@Dao
interface ExerciseDao {
    @Query("select * from exercise where name = :name")
    fun getExerciseByName(name: String?): LiveData<Exercise?>?

    @Query("select * from exercise")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise?)

    @Query("select * from exercise where id = :id")
    fun getExerciseById(id: Int): LiveData<Exercise?>?

    @Query("delete from exercise WHERE id = :id")
    fun removeExercise(id: Int)

    @Update
    suspend fun updateExercise(exercise:Exercise)

    @Insert
    fun insertInterval(interval: Interval?)

    @Insert
    fun insertAll(vararg exercises: Exercise?)

    @Query("delete from exercise")
    fun removeAll()

    @get:Query("SELECT COUNT('id') FROM exercise")
    val numberOfRows: Int

}