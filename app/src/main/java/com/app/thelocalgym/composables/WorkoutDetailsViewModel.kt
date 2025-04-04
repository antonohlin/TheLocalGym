package com.app.thelocalgym.composables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.Workout
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

    fun completeSet(id: String) {
        _workoutFlow.update {
            it.copy(exercises = it.exercises.map { exe ->
                if (exe.sets.contains(exe.sets.find { set -> set.id == id })) {
                    exe.copy(sets = exe.sets.map { set ->
                        if (set.id == id) {
                            set.copy(setCompleted = !set.setCompleted)
                        } else {
                            set
                        }
                    })
                } else {
                    exe
                }
            }) // lol
        }
    }

    fun addSet(exercise: Exercise) {
        if (exercise.sets.size == 5) return
        _workoutFlow.update {
            it.copy(exercises = it.exercises.map { exe ->
                if (exercise.id == exe.id) {
                    exe.copy(
                        sets = exe.sets.plus(
                            exe.sets.last().copy(
                                id = UUID.randomUUID().toString(), setCompleted = false
                            )
                        )
                    )
                } else {
                    exe
                }
            })
        }
    }

    fun removeSet(exercise: Exercise) {
        if (exercise.sets.size == 1) return
        _workoutFlow.update {
            it.copy(exercises = it.exercises.map { exe ->
                if (exercise.id == exe.id) {
                    exe.copy(sets = exe.sets.toMutableList().dropLast(1))
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

