package com.app.thelocalgym.composables.generics

import android.util.Range
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.WorkoutSet
import com.app.thelocalgym.repository.MockDataLayer

@Composable
fun CompactExerciseListItem(
    exercise: Exercise,
    set: WorkoutSet,
) {
    val focusManager = LocalFocusManager.current
    val interActionSource = remember {
        MutableInteractionSource()
    }
    val (weight, reps) = remember { FocusRequester.createRefs() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 5.dp)
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(8)
            )
            .clickable(
                interactionSource = interActionSource,
                indication = null,
            ) { focusManager.clearFocus() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
            text = exercise.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
        Row(
            modifier = Modifier
                .weight(3f)
                .padding(bottom = 4.dp), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SetPropertyText("Sets:", "${exercise.sets.size}")
            SetPropertyText(
                "Reps:", if (set.targetReps.lower != set.targetReps.upper) {
                    "${set.targetReps.lower}-${set.targetReps.upper}"
                } else {
                    "${set.targetReps.lower}"
                }
            )
            ExerciseTextField(
                Modifier
                    .focusRequester(weight)
                    .focusProperties { next = reps },
                set.weight,
                "weight",
                focusManager
            )
            ExerciseTextField(
                Modifier.focusRequester(reps),
                set.currentReps,
                "reps",
                focusManager
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CompactListItemPreview() {
    Column {
        CompactExerciseListItem(
            MockDataLayer.workouts.first().exercises.first(),
            MockDataLayer.workouts.first().exercises.first().sets.first().copy(targetReps = Range(3, 3)),
        )
        CompactExerciseListItem(
            MockDataLayer.workouts.first().exercises.first()
                .copy(name = "A very long exercise name that spans multiple lines"),
            MockDataLayer.workouts.first().exercises.first().sets.first(),
        )
    }
}