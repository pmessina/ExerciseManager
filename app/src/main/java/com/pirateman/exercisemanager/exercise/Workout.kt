package com.pirateman.exercisemanager.exercise

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pirateman.exercisemanager.ExerciseInterval
import java.io.Serializable


@Entity(foreignKeys = [ForeignKey(entity = ExerciseInterval::class, parentColumns = ["id"], childColumns = ["rowId"])])
class Workout(var date: String?) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

//    @Ignore
//    private val intervalIds: List<Int>? = null

    override fun toString(): String {
        return "$id"
    }


}
