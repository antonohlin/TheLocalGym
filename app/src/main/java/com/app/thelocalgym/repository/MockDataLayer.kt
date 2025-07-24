package com.app.thelocalgym.repository

import android.util.Range
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.Workout
import com.app.thelocalgym.WorkoutSet
import java.util.UUID

object MockDataLayer {
    val workouts = mutableListOf(
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Push 1",
            exercises = listOf(
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Incline Press", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Upright Row", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Triceps overhead",
                    sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Triceps push down",
                    sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Chest press", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Lat raises", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
            )
        ),
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Pull 1",
            exercises = listOf(
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Seated Row", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Biceps curl", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Lat pull down", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Rear delt fly", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
            )
        ),
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Leg 1",
            exercises = listOf(
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Squat", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Split squats", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
                Exercise(
                    id = UUID.randomUUID().toString(), name = "Calf raises", sets = listOf(
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        ),
                        WorkoutSet(
                            id = UUID.randomUUID().toString(),
                            currentReps = 10,
                            rpe = 8,
                            targetReps = Range(5, 8),
                            weight = 20
                        )
                    )
                ),
            )
        ),
    )
}