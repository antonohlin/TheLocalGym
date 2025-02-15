package com.app.thelocalgym.composables

import android.util.Range
import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.Workout
import com.app.thelocalgym.WorkoutSet
import com.app.thelocalgym.composables.screens.HomeScreen
import com.app.thelocalgym.composables.screens.WorkoutDetailsScreen
import com.app.thelocalgym.composables.screens.WorkoutsScreen
import java.util.UUID

@Composable
fun TheLocalGymNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME.name,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable(route = Routes.HOME.name) {
            HomeScreen(
                onProgramsClicked = {
                    navController.navigate(Routes.PROGRAMS.name)
                },
                onWorkoutsClicked = {
                    navController.navigate(Routes.WORKOUTS.name)
                },
                onExercisesClicked = {
                    navController.navigate(Routes.EXERCISES.name)
                },
            )
        }

        composable(route = Routes.WORKOUTS.name) {
            WorkoutsScreen(
                workouts = MockDataLayer.workouts,
                onWorkoutClicked = { workoutId ->
                    navController.navigate(Routes.WORKOUT_DETAILS.name + "/$workoutId")
                },
                navigateBack = navController::popBackStack
            )
        }

        composable(
            route = Routes.WORKOUT_DETAILS.name + "/{workoutId}",
            arguments = listOf(
                navArgument("workoutId") { type = NavType.StringType }
            ),
        ) {
            val args = it.arguments?.getString("workoutId")
            val workout = MockDataLayer.workouts.find { workout -> workout.id == args }
            val context = LocalContext.current
            workout?.let {
                val viewmodel = WorkoutDetailsViewModel(workout)
                WorkoutDetailsScreen(
                    workout = workout,
                    navigateBack = navController::popBackStack,
                    setSets = {_,_ ->}
                )
            } ?: run {
                navController.navigate(Routes.WORKOUTS.name)
                Toast.makeText(context, "Couldn't find workout", Toast.LENGTH_SHORT).show()
            }
        }

        composable(route = Routes.EXERCISES.name) {
            Text(text = "Hello")
        }

        composable(route = Routes.PROGRAMS.name) {
            Text(text = "Hello")
        }
    }
}

private enum class Routes {
    HOME,
    WORKOUTS,
    PROGRAMS,
    WORKOUT_DETAILS,
    EXERCISES,
}

object MockDataLayer { // TODO: Create repository and mock getters and setters
    val workouts = listOf(
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
                Exercise(id = UUID.randomUUID().toString(), name = "Upright Row", sets = listOf(
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
                )),
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
                Exercise(id = UUID.randomUUID().toString(), name = "Chest press", sets = listOf(
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
                )),
                Exercise(id = UUID.randomUUID().toString(), name = "Lat raises", sets = listOf(
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
                )),
            )
        ),
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Pull 1",
            exercises = listOf(
                Exercise(id = UUID.randomUUID().toString(), name = "Seated Row", sets = listOf(
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
                )),
                Exercise(id = UUID.randomUUID().toString(), name = "Biceps curl", sets = listOf(
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
                )),
                Exercise(id = UUID.randomUUID().toString(), name = "Lat pull down", sets = listOf(
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
                )),
                Exercise(id = UUID.randomUUID().toString(), name = "Rear delt fly", sets = listOf(
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
                )),
            )
        ),
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Leg 1",
            exercises = listOf(
                Exercise(id = UUID.randomUUID().toString(), name = "Squat", sets = listOf(
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
                )),
                Exercise(id = UUID.randomUUID().toString(), name = "Split squats", sets = listOf(
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
                )),
                Exercise(id = UUID.randomUUID().toString(), name = "Calf raises", sets = listOf(
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
                )),
            )
        ),
    )
}