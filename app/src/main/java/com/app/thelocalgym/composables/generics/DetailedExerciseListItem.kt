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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.R
import com.app.thelocalgym.WorkoutSet
import com.app.thelocalgym.repository.MockDataLayer
import com.app.thelocalgym.ui.theme.lightBlue
import java.util.UUID

@Composable
fun DetailedExerciseListItem(
    exercise: Exercise,
    addSet: (Exercise) -> Unit,
    removeSet: (Exercise) -> Unit,
    completeSet: (id: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val exerciseCompleted by remember(exercise.sets) {
        derivedStateOf { exercise.sets.all { it.setCompleted } }
    }
    val interActionSource = remember {
        MutableInteractionSource()
    }
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .background(
                color = if (exerciseCompleted) {
                    lightBlue
                } else {
                    MaterialTheme.colorScheme.surface
                },
                RoundedCornerShape(3)
            )
            .clickable(
                interactionSource = interActionSource,
                indication = null,
            ) { focusManager.clearFocus() }
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = exercise.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row {
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(20.dp)
                            .clickable {
                                removeSet(exercise)
                            },
                        painter = painterResource(R.drawable.remove_24px),
                        contentDescription = "Remove set icon"
                    )
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(20.dp)
                            .clickable {
                                addSet(exercise)
                            },
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add set icon"
                    )
                }
            }
            Column {
                exercise.sets.forEachIndexed { i, set ->
                    val (weight, reps, icon) = remember { FocusRequester.createRefs() }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SetPropertyText("Set:", "${i + 1}")
                        SetPropertyText("RPE:", "${set.rpe}")
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
                                .onFocusChanged { if (it.isFocused) completeSet(set.id) }
                                .focusable()
                                .clickable(
                                    indication = null,
                                    interactionSource = interActionSource,
                                ) {
                                    completeSet(set.id)
                                },
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Complete set icon",
                            tint = if (set.setCompleted || exerciseCompleted) {
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
fun SetPropertyText(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, fontSize = 12.sp)
        Text(text = value, fontSize = 12.sp)
    }
}

@Composable
fun ExerciseTextField(
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
            if (valueDisplayed.isBlank() && !isTextFieldFocused) {
                Text(
                    text = placeholderValue,
                    color = Color.Gray,
                    modifier = Modifier.padding(2.dp)
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
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExerciseListItemPreview() {
    Column {
        DetailedExerciseListItem(
            exercise = MockDataLayer.workouts.first().exercises.first(),
            addSet = {},
            removeSet = {},
            completeSet = {},
        )
        DetailedExerciseListItem(
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
            ), addSet = {},
            removeSet = {},
            completeSet = {}
        )
    }
}