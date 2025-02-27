package com.app.thelocalgym.composables

import androidx.lifecycle.ViewModel
import com.app.thelocalgym.Exercise
import com.app.thelocalgym.Workout
import com.app.thelocalgym.WorkoutRepository
import com.app.thelocalgym.WorkoutSet
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = WorkoutDetailsViewModel.WorkoutDetailsViewModelFactory::class)
class WorkoutDetailsViewModel @AssistedInject constructor(
    @Assisted private val workout: Workout,
    private val workoutRepo: WorkoutRepository
) : ViewModel() {

    private val _workoutFlow = MutableStateFlow(workout)
    val workoutFlow = _workoutFlow.asStateFlow()

    fun setNumberOfSets(exercise: Exercise, newValue: Int) {
        require(newValue > 0)
        if (exercise.sets.size == newValue) return

        val newListOfSets = if (exercise.sets.size < newValue) {
            val numberOfSetsToAdd = newValue - exercise.sets.size
            val newSets = mutableListOf<WorkoutSet>()
            for (i in 1..numberOfSetsToAdd) {
                newSets.add(exercise.sets.last())
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

    @AssistedFactory
    interface WorkoutDetailsViewModelFactory {
        fun create(workout: Workout): WorkoutDetailsViewModel
    }
}

