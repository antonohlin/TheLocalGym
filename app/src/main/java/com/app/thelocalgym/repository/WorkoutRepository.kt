package com.app.thelocalgym.repository

import com.app.thelocalgym.Workout
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

interface WorkoutRepository {
    suspend fun getWorkout(id: String): Workout?
}

class LocalWorkoutRepository @Inject constructor() : WorkoutRepository {
    override suspend fun getWorkout(id: String): Workout? {
        // todo: implement
        return null
    }
}

class TestWorkoutRepository() : WorkoutRepository {
    override suspend fun getWorkout(id: String): Workout? {
        // todo: implement
        return null
    }
}

class MockWorkoutRepository @Inject constructor() : WorkoutRepository {
    override suspend fun getWorkout(id: String): Workout? {
        return MockDataLayer.workouts.find { it.id == id }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class WorkoutRepositoryModule {

    @Binds
    abstract fun bindWorkoutRepository(
        workoutRepository: MockWorkoutRepository
    ): WorkoutRepository
}