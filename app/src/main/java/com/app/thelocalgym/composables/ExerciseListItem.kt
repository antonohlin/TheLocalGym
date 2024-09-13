package com.app.thelocalgym.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.thelocalgym.Exercise

@Composable
fun ExerciseListItem(
    exercise: Exercise,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = exercise.name, modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = exercise.reps.toString(), modifier = Modifier
                    .padding(10.dp)
                    .weight(0.5f)
            )
            Text(
                text = exercise.sets.toString(), modifier = Modifier
                    .padding(10.dp)
                    .weight(0.5f)
            )
        }
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun ExerciseListItemPreview() {
    Column {
        ExerciseListItem(exercise = MockDataLayer.workouts.first().exercises.first())
        ExerciseListItem(exercise = MockDataLayer.workouts.first().exercises.first())
    }
}