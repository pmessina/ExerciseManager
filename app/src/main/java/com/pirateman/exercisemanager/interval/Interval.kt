package com.pirateman.exercisemanager.interval

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.pirateman.exercisemanager.exercise.Exercise

@Entity(indices = [Index("exerciseId")], foreignKeys = [ForeignKey(entity = Exercise::class, parentColumns = arrayOf("id"), childColumns = arrayOf("exerciseId"), onDelete = ForeignKey.CASCADE)])
class Interval {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var exerciseId = 0
    var reps = 0
    var sets = 0
    var weight = 0
    var date: String? = null

}