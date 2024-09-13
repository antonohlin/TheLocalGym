package com.app.thelocalgym.composables.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.thelocalgym.Workout
import com.app.thelocalgym.composables.ClickableListItem
import com.app.thelocalgym.composables.MockDataLayer

@Composable
fun WorkoutsScreen(
    workouts: List<Workout>,
    onWorkoutClicked: (String) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(workouts){
            ClickableListItem(text = it.name, onClick = { onWorkoutClicked(it.id) })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WorkoutsScreenPreview() {
    WorkoutsScreen(
        workouts = MockDataLayer.workouts,
        onWorkoutClicked = {},
    )
}