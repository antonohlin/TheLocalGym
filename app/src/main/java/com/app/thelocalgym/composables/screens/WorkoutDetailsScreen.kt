package com.app.thelocalgym.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.R
import com.app.thelocalgym.Workout
import com.app.thelocalgym.composables.WorkoutDetailsOperationState
import com.app.thelocalgym.composables.WorkoutListType
import com.app.thelocalgym.composables.generics.CompactExerciseListItem
import com.app.thelocalgym.composables.generics.DetailedExerciseListItem
import com.app.thelocalgym.composables.generics.TopBar
import com.app.thelocalgym.repository.MockDataLayer

@Composable
fun WorkoutDetailsScreen(
    viewState: WorkoutDetailsOperationState,
    workout: Workout,
    navigateBack: () -> Unit,
    addSet: (Exercise) -> Unit,
    removeSet: (Exercise) -> Unit,
    completeSet: (id: String) -> Unit,
    listType: WorkoutListType,
    setListType: (WorkoutListType) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                onLeftClick = navigateBack,
                showLeftIcon = true,
            )
        }
    ) { padding ->
        if (viewState is WorkoutDetailsOperationState.Success) {
            LazyColumn(modifier = Modifier.padding(padding)) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = workout.name,
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        ListTypeIcon(listType, setListType)
                    }
                }
                items(items = workout.exercises, key = { it.id }) {
                    if (listType == WorkoutListType.DETAILED) {
                        DetailedExerciseListItem(
                            exercise = it,
                            addSet = addSet,
                            removeSet = removeSet,
                            completeSet = completeSet,
                        )
                    } else {
                        CompactExerciseListItem(
                            exercise = it,
                            set = it.sets.first() // todo: not safe
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ListTypeIcon(
    listType: WorkoutListType,
    setListType: (WorkoutListType) -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            val newValue = if (listType == WorkoutListType.DETAILED) {
                WorkoutListType.COMPACT
            } else {
                WorkoutListType.DETAILED
            }
            setListType(newValue)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (listType == WorkoutListType.DETAILED) {
                "Detailed"
            } else {
                "Compact"
            },
            style = TextStyle.Default.copy(fontWeight = FontWeight.Bold)
        )
        if (listType == WorkoutListType.DETAILED) {
            Icon(
                modifier = Modifier
                    .padding(5.dp)
                    .size(20.dp),
                painter = painterResource(R.drawable.format_list_numbered_24px),
                contentDescription = "Detailed list icon"
            )
        } else {
            Icon(
                modifier = Modifier
                    .padding(5.dp)
                    .size(20.dp),
                imageVector = Icons.Default.Menu,
                contentDescription = "Compact list icon"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutDetailsSuccessScreenPreview() {
    WorkoutDetailsScreen(
        viewState = WorkoutDetailsOperationState.Success,
        workout = MockDataLayer.workouts.first(),
        navigateBack = {},
        addSet = {},
        removeSet = {},
        completeSet = {},
        listType = WorkoutListType.DETAILED,
        setListType = {},
    )
}
