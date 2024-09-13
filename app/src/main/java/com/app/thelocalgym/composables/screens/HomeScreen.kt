package com.app.thelocalgym.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.thelocalgym.composables.ClickableListItem
import com.app.thelocalgym.composables.TopBar

@Composable
fun HomeScreen(
    onWorkoutsClicked: () -> Unit,
    onExercisesClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ClickableListItem("Workouts", onWorkoutsClicked)
            ClickableListItem("Exercises", onExercisesClicked)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun HomeScreenPreview() {
    HomeScreen(
        onWorkoutsClicked = {},
        onExercisesClicked = {},
    )
}