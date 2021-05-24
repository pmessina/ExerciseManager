package com.pirateman.exercisemanager.exercise

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pirateman.exercisemanager.interval.Interval
import com.pirateman.exercisemanager.interval.IntervalDao

@Database(entities = [Exercise::class, Interval::class], version = 9, exportSchema = false)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun intervalDao(): IntervalDao


}