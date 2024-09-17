package com.app.thelocalgym.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Exercise

@Composable
fun ExerciseListItem(
    exercise: Exercise,
) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .padding(horizontal = 8.dp)
            .background(Color(0xFFd2edfc), RoundedCornerShape(5))

    ) {
        Column {
            Text(
                text = exercise.name,
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .padding(start = 15.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Counter(exercise.weight, "weight")
                Counter(exercise.reps, "reps")
                Counter(exercise.sets, "sets")
            }
        }
    }
}

@Composable
private fun Counter(value: Int, title: String) {
    var valueDisplayed by remember {
        mutableStateOf(value.toString())
    }
    Column {
        Text(text = title.plus(":"), modifier = Modifier.padding(bottom = 1.dp))
        Box(
            modifier = Modifier
                .background(Color.White)
                .border(
                    width = 1.dp,
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(10)
                ),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                modifier = Modifier
                    .padding(5.dp)

                    .width(75.dp)
                    .height(20.dp),
                value = valueDisplayed,
                onValueChange = { valueDisplayed = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExerciseListItemPreview() {
    Column {
        ExerciseListItem(exercise = MockDataLayer.workouts.first().exercises.first())
        ExerciseListItem(exercise = MockDataLayer.workouts.first().exercises.first().copy(reps = 1234, sets = 4321))
    }
}