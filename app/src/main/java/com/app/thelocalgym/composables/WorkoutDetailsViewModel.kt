package com.app.thelocalgym.composables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.Workout
import com.app.thelocalgym.WorkoutSet
import com.app.thelocalgym.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailsViewModel @Inject constructor(
    private val workoutRepo: WorkoutRepository,
) : ViewModel() {

    private val _workoutFlow = MutableStateFlow(Workout.default())
    val workoutFlow = _workoutFlow.asStateFlow()

    private val _viewState: MutableStateFlow<WorkoutDetailsViewState> =
        MutableStateFlow(WorkoutDetailsViewState.Initial)
    val viewState = _viewState.asStateFlow()

    private suspend fun getWorkout(id: String): Workout? {
        return workoutRepo.getWorkout(id)
    }

    private fun setWorkout(workout: Workout) {
        _workoutFlow.update { workout }
    }

    private fun setViewState(state: WorkoutDetailsViewState) {
        _viewState.update { state }
    }

    fun initWorkoutDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) { // todo: Inject dispatcher
            getWorkout(id)?.let {
                setWorkout(it)
                setViewState(WorkoutDetailsViewState.Success)
            } ?: setViewState(WorkoutDetailsViewState.Error)
        }
    }

    fun setNumberOfSets(exercise: Exercise, newValue: Int) {
        require(newValue > 0)
        if (exercise.sets.size == newValue) return

        val newListOfSets = if (exercise.sets.size < newValue) {
            val numberOfSetsToAdd = newValue - exercise.sets.size
            val newSets = mutableListOf<WorkoutSet>()
            for (i in 1..numberOfSetsToAdd) {
                newSets.add(exercise.sets.last().copy(id = UUID.randomUUID().toString()))
            }
            exercise.sets.plus(newSets)
        } else {
            val numberOfSetsToDrop = exercise.sets.size - newValue
            exercise.sets.dropLast(numberOfSetsToDrop)
        }
        // todo: write and read to/from local db
        _workoutFlow.update {
            it.copy(exercises = it.exercises.map { exe ->
                if (exe.id == exercise.id) {
                    exe.copy(sets = newListOfSets)
                } else {
                    exe
                }
            })
        }
    }
}

sealed interface WorkoutDetailsViewState {
    data object Initial : WorkoutDetailsViewState
    data object Loading : WorkoutDetailsViewState
    data object Success : WorkoutDetailsViewState
    data object Error : WorkoutDetailsViewState
}

