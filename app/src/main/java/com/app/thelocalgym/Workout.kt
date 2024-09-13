package com.app.thelocalgym

data class Workout(
    val id: String,
    val name: String,
    val exercises: List<Exercise>
)

data class Exercise(
    val name: String,
    val sets: Int,
    val reps: Int,
)
