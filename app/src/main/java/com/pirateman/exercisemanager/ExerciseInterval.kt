package com.pirateman.exercisemanager

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.util.concurrent.ThreadLocalRandom

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class ExerciseInterval : Serializable {
    var rowId = 0
        private set
    var activity: String? = null
        private set
    var speed = 0.0
    var incline = 0.0
    var duration = 0

    constructor() {
        this.randomize()
    }

    constructor(speed: Double, incline: Double, duration: Int) {
        randomize(speed, incline, duration)
    }

    constructor(intervals: Int, speed: Double, incline: Double, duration: Int) {
        randomize(speed, incline, duration, intervals)
    }

    constructor(rowId: Int, intervals: Int, speed: Double, incline: Double, duration: Int) {
        this.rowId = rowId
        this.speed = speed
        this.incline = incline
        this.duration = duration
        activity = setActivity(speed)
    }

    //The activity field is set based on the speed
    private fun setActivity(speed: Double): String {
        if (speed < 3.0) return "Walk"
        if (speed >= 3.0 && speed < 5.0) return "Jog"
        return if (speed >= 5.0 && speed < 7.0) "Run" else "Sprint"
    }

    //randomize based on hard coded values
    private fun randomize() {
        val incline = doubleArrayOf(0.0, 0.5, 1.0, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0, 11.5, 12.0, 12.5, 13.0, 13.5, 14.0, 15.0)
        val speed = doubleArrayOf(0.0, 0.5, 1.0, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0, 8.5, 9.0, 9.5, 10.0, 10.5, 11.0, 11.5, 12.0)
        val speedIndex = ThreadLocalRandom.current().nextInt(1, speed.size)
        val inclineIndex = ThreadLocalRandom.current().nextInt(0, incline.size)
        val randDuration = ThreadLocalRandom.current().nextInt(3, 8)
        val selectedSpeed = speed[speedIndex]
        val selectedIncline = incline[inclineIndex]
        this.speed = selectedSpeed
        this.incline = selectedIncline
        duration = randDuration
        activity = setActivity(selectedSpeed)
    }

    private fun randomize(speed: Double, incline: Double, duration: Int) {
        val randSpeed = ThreadLocalRandom.current().nextDouble(1.0, speed)
        val roundedRandSpeed = Math.round(randSpeed * 2) / 2.0
        val randIncline = ThreadLocalRandom.current().nextDouble(0.0, incline)
        val roundedIncline = Math.round(randIncline * 2) / 2.0
        val randDuration = ThreadLocalRandom.current().nextInt(1, duration)
        this.speed = roundedRandSpeed
        this.incline = roundedIncline
        this.duration = randDuration
        activity = setActivity(randSpeed)
    }

    private fun randomize(speed: Double, incline: Double, duration: Int, intervals: Int) {
        val randSpeed = ThreadLocalRandom.current().nextDouble(1.0, speed)
        val roundedRandSpeed = Math.round(randSpeed * 2) / 2.0
        val randIncline = ThreadLocalRandom.current().nextDouble(0.0, incline)
        val roundedIncline = Math.round(randIncline * 2) / 2.0
        val randDuration = ThreadLocalRandom.current().nextInt(1, duration)
        this.speed = roundedRandSpeed
        this.incline = roundedIncline
        this.duration = randDuration
        activity = setActivity(randSpeed)
    }
}