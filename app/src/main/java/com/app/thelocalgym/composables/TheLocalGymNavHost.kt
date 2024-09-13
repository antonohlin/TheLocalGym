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
    }
}

private enum class Routes {
    HOME,
    WORKOUTS,
    WORKOUT_DETAILS,
    EXERCISES,
}

object MockDataLayer {
    val workouts = listOf(
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Push 1",
            exercises = listOf(
                Exercise(name = "Incline Press", sets = 3, reps = 10),
                Exercise(name = "Upright Row", sets = 3, reps = 15),
                Exercise(name = "Triceps overhead", sets = 2, reps = 10),
                Exercise(name = "Triceps push down", sets = 2, reps = 10),
                Exercise(name = "Chest press", sets = 3, reps = 10),
                Exercise(name = "Lat raises", sets = 3, reps = 15),
            )
        ),
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Pull 1",
            exercises = listOf(
                Exercise(name = "Seated Row", sets = 3, reps = 10),
                Exercise(name = "Biceps curl", sets = 3, reps = 10),
                Exercise(name = "Lat pull down", sets = 3, reps = 10),
                Exercise(name = "Rear delt fly", sets = 3, reps = 15),
            )
        ),
        Workout(
            id = UUID.randomUUID().toString(),
            name = "Leg 1",
            exercises = listOf(
                Exercise(name = "Squat", sets = 3, reps = 5),
                Exercise(name = "Split squats", sets = 3, reps = 10),
                Exercise(name = "Calf raises", sets = 4, reps = 10),
            )
        ),
    )
}