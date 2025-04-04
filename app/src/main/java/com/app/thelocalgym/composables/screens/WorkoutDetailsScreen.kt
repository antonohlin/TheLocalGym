package com.app.thelocalgym.composables.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.Workout
import com.app.thelocalgym.composables.WorkoutDetailsViewState
import com.app.thelocalgym.composables.generics.ExerciseListItem
import com.app.thelocalgym.composables.generics.TopBar
import com.app.thelocalgym.repository.MockDataLayer

@Composable
fun WorkoutDetailsScreen(
    viewState: WorkoutDetailsViewState,
    workout: Workout,
    navigateBack: () -> Unit,
    addSet: (Exercise) -> Unit,
    removeSet: (Exercise) -> Unit,
    completeSet: (id: String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                onLeftClick = navigateBack,
                showLeftIcon = true,
            )
        }
    ) { padding ->
        if (viewState is WorkoutDetailsViewState.Success) {
            LazyColumn(modifier = Modifier.padding(padding)) {
                item {
                    Text(
                        text = workout.name,
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                items(items = workout.exercises, key = { it.id }) {
                    ExerciseListItem(
                        exercise = it,
                        addSet = addSet,
                        removeSet = removeSet,
                        completeSet = completeSet,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutDetailsSuccessScreenPreview() {
    WorkoutDetailsScreen(
        viewState = WorkoutDetailsViewState.Success,
        workout = MockDataLayer.workouts.first(),
        navigateBack = {},
        addSet = {},
        removeSet = {},
        completeSet = {}
    )
}
