package com.app.thelocalgym

import android.util.Range

data class Workout(
    val id: String,
    val name: String,
    val exercises: List<Exercise>
)

data class Exercise(
    val id: String,
    val name: String,
    val sets: List<WorkoutSet>,
)

data class Program(
    val id: String,
    val name: String,
    val workouts: List<Workout>
)

data class WorkoutSet(
    val id: String,
    val currentReps: Int,
    val rpe: Int,
    val targetReps: Range<Int>,
    val weight: Int,
)
