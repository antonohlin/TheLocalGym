package com.app.thelocalgym.repository

import com.app.thelocalgym.Workout
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

interface WorkoutRepository {
    suspend fun getWorkout(id: String): Workout?
    suspend fun setWorkout(workout: Workout)
    suspend fun setAndGetWorkout(workout: Workout): Workout
    suspend fun addWorkout(workout: Workout)
    suspend fun deleteWorkout(id: String)
}

class LocalWorkoutRepository @Inject constructor() : WorkoutRepository {
    override suspend fun getWorkout(id: String): Workout? {
        // todo: implement
        return null
    }

    override suspend fun setWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun setAndGetWorkout(workout: Workout): Workout {
        TODO("Not yet implemented")
    }

    override suspend fun addWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWorkout(id: String) {
        TODO("Not yet implemented")
    }
}

class TestWorkoutRepository() : WorkoutRepository {
    override suspend fun getWorkout(id: String): Workout? {
        // todo: implement
        return null
    }

    override suspend fun setWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun setAndGetWorkout(workout: Workout): Workout {
        TODO("Not yet implemented")
    }

    override suspend fun addWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWorkout(id: String) {
        TODO("Not yet implemented")
    }
}

class MockWorkoutRepository @Inject constructor() : WorkoutRepository {
    override suspend fun getWorkout(id: String): Workout? {
        return MockDataLayer.workouts.find { it.id == id }
    }

    override suspend fun setWorkout(workout: Workout) {
        MockDataLayer.workouts.map {
            if (it.id == workout.id) {
                workout
            } else {
                it
            }
        }
    }

    override suspend fun setAndGetWorkout(workout: Workout): Workout {
        setWorkout(workout)
        return workout
    }

    override suspend fun addWorkout(workout: Workout) {
        MockDataLayer.workouts.add(workout)
    }

    override suspend fun deleteWorkout(id: String) {
        MockDataLayer.workouts.removeIf { it.id == id }
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