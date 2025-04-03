package com.app.thelocalgym.composables.generics

import android.util.Range
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.WorkoutSet
import com.app.thelocalgym.repository.MockDataLayer
import com.app.thelocalgym.ui.theme.lightBlue
import java.util.UUID

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExerciseListItem(
    exercise: Exercise,
    setSets: (Exercise, Int) -> Unit,
) {
    var completedSets by remember {
        mutableIntStateOf(0)
    }

    var exerciseCompleted by remember(exercise.sets.size) {
        mutableStateOf(completedSets == exercise.sets.size)
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
                    selectedSetsRange = exercise.sets.size,
                    setSets = { setSets(exercise, it) },
                )
            }
            Column {
                exercise.sets.forEachIndexed { i, set ->
                    val (weight, reps, icon) = remember { FocusRequester.createRefs() }
                    var setCompleted by remember {
                        mutableStateOf(false)
                    }

                    fun toggleIcon() {
                        setCompleted = !setCompleted
                        if (setCompleted) {
                            completedSets++
                            if (completedSets == exercise.sets.size) {
                                exerciseCompleted = true
                            }
                        } else {
                            completedSets--
                            exerciseCompleted = false
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SetPropertyText("Set:", "${i + 1}")
                        SetPropertyText("RPE:", "${set.rpe}")
                        SetPropertyText("Range:", "${set.targetReps.lower}-${set.targetReps.upper}")
                        ExerciseTextField(
                            Modifier
                                .focusRequester(weight)
                                .focusProperties { next = reps },
                            set.weight,
                            "weight",
                            focusManager
                        )
                        ExerciseTextField(
                            Modifier
                                .focusRequester(reps)
                                .focusProperties { next = icon },
                            set.currentReps,
                            "reps",
                            focusManager
                        )
                        Icon(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .size(20.dp)
                                .focusRequester(icon)
                                .onFocusChanged { if (it.isFocused) toggleIcon() }
                                .focusable()
                                .clickable(
                                    indication = null,
                                    interactionSource = interActionSource,
                                ) {
                                    toggleIcon()
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
private fun SetPropertyText(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, fontSize = 12.sp)
        Text(text = value, fontSize = 12.sp)
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
    modifier: Modifier,
    value: Int,
    title: String,
    focusManager: FocusManager,
) {
    var valueDisplayed by remember {
        mutableStateOf("")
    }
    var isTextFieldFocused by remember {
        mutableStateOf(false)
    }
    var showPlaceholder by remember {
        mutableStateOf(true)
    }
    val placeholderValue by remember {
        mutableStateOf(value.toString())
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
            if (showPlaceholder) {
                BasicTextField(
                    modifier = Modifier
                        .padding(2.dp)
                        .width(40.dp)
                        .height(20.dp),
                    value = placeholderValue,
                    onValueChange = { },
                    readOnly = true,
                    singleLine = true,
                    textStyle = TextStyle.Default.copy(color = Color.Gray)
                )
            }
            BasicTextField(
                modifier = modifier
                    .padding(2.dp)
                    .width(40.dp)
                    .height(20.dp)
                    .onFocusChanged { focusState ->
                        isTextFieldFocused = focusState.isFocused
                    },
                value = valueDisplayed,
                onValueChange = {
                    valueDisplayed = it
                    showPlaceholder = valueDisplayed.isBlank()
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) })
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExerciseListItemPreview() {
    Column {
        ExerciseListItem(exercise = MockDataLayer.workouts.first().exercises.first(), setSets = { _, _ -> })
        ExerciseListItem(
            exercise = MockDataLayer.workouts.first().exercises.first().copy(
                sets = listOf(
                    WorkoutSet(
                        id = UUID.randomUUID().toString(),
                        currentReps = 10,
                        rpe = 8,
                        targetReps = Range(5, 8),
                        weight = 20
                    ),
                    WorkoutSet(
                        id = UUID.randomUUID().toString(),
                        currentReps = 10,
                        rpe = 8,
                        targetReps = Range(5, 8),
                        weight = 20
                    ),
                    WorkoutSet(
                        id = UUID.randomUUID().toString(),
                        currentReps = 10,
                        rpe = 8,
                        targetReps = Range(5, 8),
                        weight = 20
                    )
                )
            ), setSets = { _, _ -> }
        )
    }
}