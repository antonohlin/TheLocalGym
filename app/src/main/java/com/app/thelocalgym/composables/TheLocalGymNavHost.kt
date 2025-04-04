package com.app.thelocalgym.composables

import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.thelocalgym.composables.screens.HomeScreen
import com.app.thelocalgym.composables.screens.WorkoutDetailsScreen
import com.app.thelocalgym.composables.screens.WorkoutsScreen
import com.app.thelocalgym.repository.MockDataLayer

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
            val context = LocalContext.current
            args?.let { workoutId ->
                val viewModel: WorkoutDetailsViewModel = hiltViewModel()
                LaunchedEffect(Unit) {
                    viewModel.initWorkoutDetails(workoutId)
                }
                val state by viewModel.uiState.collectAsState()
                WorkoutDetailsScreen(
                    viewState = state.viewState,
                    workout = state.workout,
                    navigateBack = navController::popBackStack,
                    addSet = viewModel::addSet,
                    removeSet = viewModel::removeSet,
                    completeSet = viewModel::completeSet,
                    listType = state.listType,
                    setListType = viewModel::setListType
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
