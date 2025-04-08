package com.app.thelocalgym.composables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.Workout
import com.app.thelocalgym.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailsViewModel @Inject constructor(
    private val workoutRepo: WorkoutRepository,
) : ViewModel() {

    private val _workoutFlow = MutableStateFlow(Workout.default())

    private val _viewState: MutableStateFlow<WorkoutDetailsOperationState> =
        MutableStateFlow(WorkoutDetailsOperationState.Initial)

    private val _listTypeState = MutableStateFlow(WorkoutListType.COMPACT)

    val uiState = combine(
        _viewState,
        _workoutFlow,
        _listTypeState
    ) { view, workout, listType ->
        WorkoutDetailsViewState(view, workout, listType)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = WorkoutDetailsViewState()
    )


    private suspend fun getWorkout(id: String): Workout? {
        return workoutRepo.getWorkout(id)
    }

    private fun setWorkout(workout: Workout) {
        _workoutFlow.update { workout }
    }

    private fun setViewState(state: WorkoutDetailsOperationState) {
        _viewState.update { state }
    }

    fun initWorkoutDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) { // todo: Inject dispatcher
            getWorkout(id)?.let {
                setWorkout(it)
                setViewState(WorkoutDetailsOperationState.Success)
            } ?: setViewState(WorkoutDetailsOperationState.Error)
        }
    }

    fun setListType(listType: WorkoutListType) {
        _listTypeState.update { listType }
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

sealed interface WorkoutDetailsOperationState {
    data object Initial : WorkoutDetailsOperationState
    data object Loading : WorkoutDetailsOperationState
    data object Success : WorkoutDetailsOperationState
    data object Error : WorkoutDetailsOperationState
}

data class WorkoutDetailsViewState(
    val viewState: WorkoutDetailsOperationState = WorkoutDetailsOperationState.Initial,
    val workout: Workout = Workout.default(),
    val listType: WorkoutListType = WorkoutListType.COMPACT,
)

enum class WorkoutListType {
    DETAILED,
    COMPACT
}

