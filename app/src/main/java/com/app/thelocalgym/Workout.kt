package com.app.thelocalgym

data class Workout(
    val id: String,
    val name: String,
    val exercises: List<Exercise>
)

data class Exercise( // RPE/RIR?
    val id: String,
    val name: String,
    val weight: Int,
    val sets: Int,
    val reps: Int,
)

data class Program(
    val id: String,
    val name: String,
    val workouts: List<Workout>
)
