package com.app.thelocalgym.composables

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.Workout
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
            WorkoutDetailsScreen(
                workout = workout,
                navigateBack = navController::popBackStack
            )
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
                Exercise(id = UUID.randomUUID().toString(), name = "Incline Press", weight = 20, sets = 3, reps = 10),
                Exercise(id = UUID.randomUUID().toString(), name = "Upright Row", weight = 20, sets = 3, reps = 15),
                Exercise(id = UUID.randomUUID().toString(), name = "Triceps overhead", weight = 20, sets = 2, reps = 10),
                Exercise(id = UUID.randomUUID().toString(), name = "Triceps push down", weight = 20, sets = 2, reps = 10),
                Exercise(id = UUID.randomUUID().toString(), name = "Chest press", weight = 20, sets = 3, reps = 10),
                Exercise(id = UUID.randomUUID().toString(), name = "Lat raises", weight = 20, sets = 3, reps = 15),
            )
        ),
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Pull 1",
            exercises = listOf(
                Exercise(id = UUID.randomUUID().toString(), name = "Seated Row", weight = 20, sets = 3, reps = 10),
                Exercise(id = UUID.randomUUID().toString(), name = "Biceps curl", weight = 20, sets = 3, reps = 10),
                Exercise(id = UUID.randomUUID().toString(), name = "Lat pull down", weight = 20, sets = 3, reps = 10),
                Exercise(id = UUID.randomUUID().toString(), name = "Rear delt fly", weight = 20, sets = 3, reps = 15),
            )
        ),
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Leg 1",
            exercises = listOf(
                Exercise(id = UUID.randomUUID().toString(), name = "Squat", weight = 20, sets = 3, reps = 5),
                Exercise(id = UUID.randomUUID().toString(), name = "Split squats", weight = 20, sets = 3, reps = 10),
                Exercise(id = UUID.randomUUID().toString(), name = "Calf raises", weight = 20, sets = 4, reps = 10),
            )
        ),
    )
}