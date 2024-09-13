package com.app.thelocalgym.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.thelocalgym.Workout
import com.app.thelocalgym.composables.MockDataLayer

@Composable
fun WorkoutDetailsScreen(workout: Workout?) {
    workout?.let {
        LazyColumn {
            item {
                Text(text = it.name, modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold)
            }
            items(it.exercises) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutDetailsScreenPreview() {
    WorkoutDetailsScreen(
        MockDataLayer.workouts.first()
    )
}