package com.app.thelocalgym.composables.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Workout
import com.app.thelocalgym.composables.ExerciseListItem
import com.app.thelocalgym.composables.MockDataLayer
import com.app.thelocalgym.composables.TopBar

@Composable
fun WorkoutDetailsScreen(
    workout: Workout?,
    navigateBack: () -> Unit,
    ) {
    Scaffold(
        topBar = { TopBar(onLeftClick = navigateBack) }
    ) { padding ->
        workout?.let {
            LazyColumn(modifier = Modifier.padding(padding)) {
                item {
                    Text(
                        text = it.name,
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                items(it.exercises) {
                    ExerciseListItem(exercise = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutDetailsScreenPreview() {
    WorkoutDetailsScreen(
        MockDataLayer.workouts.first(),
        navigateBack = {},
    )
}