package com.app.thelocalgym.composables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.thelocalgym.composables.generics.ClickableListItem
import com.app.thelocalgym.composables.generics.TopBar
import com.app.thelocalgym.composables.generics.VerticalSpacer

@Composable
fun HomeScreen(
    onProgramsClicked: () -> Unit,
    onWorkoutsClicked: () -> Unit,
    onExercisesClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            VerticalSpacer(height = 5) // TODO: Add Icon at start/end?
            ClickableListItem("Programs", onProgramsClicked)
            ClickableListItem("Workouts", onWorkoutsClicked)
            ClickableListItem("Exercises", onExercisesClicked)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun HomeScreenPreview() {
    HomeScreen(
        onProgramsClicked = {},
        onWorkoutsClicked = {},
        onExercisesClicked = {},
    )
}