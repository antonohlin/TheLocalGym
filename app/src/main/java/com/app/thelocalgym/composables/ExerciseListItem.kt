package com.app.thelocalgym.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Exercise

@Composable
fun ExerciseListItem(
    exercise: Exercise,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = exercise.name,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(0.3f))
            Counter(exercise.reps)
            Counter(exercise.sets)
            Spacer(modifier = Modifier.weight(0.3f))
        }
        Divider()
    }
}

@Composable
private fun Counter(value: Int) {
    Box(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .padding(end = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(30.dp)
                .width(60.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(20))
                .clickable { /* TODO: Edit value */ },
        )
        Text(text = value.toString(), modifier = Modifier.padding(10.dp))
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