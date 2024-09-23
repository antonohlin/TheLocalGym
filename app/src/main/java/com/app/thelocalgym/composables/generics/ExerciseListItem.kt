package com.app.thelocalgym.composables.generics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.composables.MockDataLayer
import com.app.thelocalgym.ui.theme.lightBlue

@Composable
fun ExerciseListItem(
    exercise: Exercise,
) {
    var sets by remember {
        mutableIntStateOf(exercise.sets)
    }
    var exerciseCompleted by remember {
        mutableStateOf(false)
    }
    var completedSets by remember {
        mutableIntStateOf(0)
    }

    fun setSets(value: Int) { // TODO: Dummy data func, remove. Prolly the above variable too in favour of flow to comp
        sets = value
        if (completedSets > value) {
            completedSets = value
        }
        exerciseCompleted = completedSets == sets
    }

    val focusManager = LocalFocusManager.current
    val interActionSource = remember {
        MutableInteractionSource()
    }
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .padding(horizontal = 8.dp)
            .background(
                color = if (exerciseCompleted) {
                    lightBlue
                } else {
                    MaterialTheme.colorScheme.surface
                },
                RoundedCornerShape(1)
            )
            .clickable(
                interactionSource = interActionSource,
                indication = null,
            ) { focusManager.clearFocus() }
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = exercise.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                SetsDropDown(
                    selectedSetsRange = sets,
                    setSets = { setSets(it) },
                )
            }
            Column {
                for (i in 1..sets) {
                    var setCompleted by remember { // TODO: Where should this live?!
                        mutableStateOf(false)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Set:", fontSize = 12.sp)
                            Text(text = "$i", fontSize = 12.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "RPE:", fontSize = 12.sp)
                            Text(text = "8", fontSize = 12.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Range:", fontSize = 12.sp)
                            Text(text = "5-8", fontSize = 12.sp)
                        }
                        ExerciseTextField(exercise.weight, "weight")
                        ExerciseTextField(exercise.reps, "reps")
                        Icon(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .size(20.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = interActionSource,
                                ) {
                                    setCompleted = !setCompleted
                                    if (setCompleted) {
                                        completedSets++
                                        if (completedSets == sets) {
                                            exerciseCompleted = true
                                        }
                                    } else {
                                        completedSets--
                                        exerciseCompleted = false
                                    }
                                },
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "checkCircle",
                            tint = if (setCompleted || exerciseCompleted) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color.LightGray
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SetsDropDown(
    selectedSetsRange: Int,
    setSets: (Int) -> Unit,
) {
    var showSetsDropDown by remember {
        mutableStateOf(false)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Sets: ", fontSize = 12.sp)
        Box(
            modifier = Modifier
                .background(Color.White)
                .border(
                    width = 1.dp,
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(10)
                )
                .clickable { showSetsDropDown = true },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = selectedSetsRange.toString(),
                modifier = Modifier.padding(5.dp),
                fontSize = 12.sp,
            )
            DropdownMenu(
                modifier = Modifier.width(70.dp),
                expanded = showSetsDropDown,
                onDismissRequest = { showSetsDropDown = false }
            ) {
                for (i in 1..5) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "$i")
                        },
                        onClick = {
                            setSets(i)
                            showSetsDropDown = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ExerciseTextField(
    value: Int,
    title: String,
) {
    val focusManager = LocalFocusManager.current
    var valueDisplayed by remember {
        mutableStateOf(value.toString())
    }
    var isTextFieldFocused by remember {
        mutableStateOf(false)
    }

    Column {
        Text(
            text = title.plus(":"),
            modifier = Modifier.padding(bottom = 1.dp),
            fontSize = 12.sp,
        )
        Box(
            modifier = Modifier
                .background(Color.White)
                .border(
                    width = if (isTextFieldFocused) 2.dp else 1.dp,
                    color = if (isTextFieldFocused) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    shape = RoundedCornerShape(10)
                ),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                modifier = Modifier
                    .padding(2.dp)
                    .width(40.dp)
                    .height(20.dp)
                    .onFocusChanged { focusState ->
                        isTextFieldFocused = focusState.isFocused
                    },
                value = valueDisplayed,
                onValueChange = { valueDisplayed = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExerciseListItemPreview() {
    Column {
        ExerciseListItem(exercise = MockDataLayer.workouts.first().exercises.first())
        ExerciseListItem(
            exercise = MockDataLayer.workouts.first().exercises.first().copy(weight = 888, reps = 123, sets = 2)
        )
    }
}