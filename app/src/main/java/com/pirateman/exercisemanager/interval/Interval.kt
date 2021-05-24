package com.pirateman.exercisemanager.interval

import androidx.room.*
import com.pirateman.exercisemanager.exercise.Exercise

@Entity(indices = [Index("exerciseId")],
    foreignKeys = [ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exerciseId"], onDelete = ForeignKey.CASCADE)]
    )

data class Interval(var reps: Int = 0, var sets: Int = 0, var weight: Int = 0, var date: String? = null) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

//    @ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exerciseId"], onDelete = ForeignKey.CASCADE)
    var exerciseId: Long = 0


}

data class ExerciseWithIntervals(@Embedded val exercise: Exercise, @Relation(parentColumn = "id", entityColumn = "exerciseId") val intervals: List<Interval>) {

}