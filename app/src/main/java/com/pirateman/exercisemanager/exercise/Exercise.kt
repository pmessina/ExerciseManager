package com.pirateman.exercisemanager.exercise

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.pirateman.exercisemanager.ExerciseInterval
import java.io.Serializable

@Entity
class Exercise(val name: String?, val muscleGroup: String?, val method: String?) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ForeignKey(entity = ExerciseInterval::class, parentColumns = ["id"], childColumns = ["rowId"])
    @Ignore
    private val intervalIds: List<Int>? = null

    override fun toString(): String {
        return "$id $name $method $muscleGroup"
    }
}