package com.app.thelocalgym.composables

import androidx.lifecycle.ViewModel
import com.app.thelocalgym.Workout
import com.app.thelocalgym.WorkoutRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = WorkoutDetailsViewModel.WorkoutDetailsViewModelFactory::class)
class WorkoutDetailsViewModel @AssistedInject constructor(
    @Assisted private val workout: Workout,
    private val workoutRepo: WorkoutRepository
) : ViewModel() {

    fun getWorkout() = workout

    @AssistedFactory
    interface WorkoutDetailsViewModelFactory {
        fun create(workout: Workout): WorkoutDetailsViewModel
    }
}

