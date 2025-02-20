package com.app.thelocalgym

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

interface WorkoutRepository {}

class LocalWorkoutRepository @Inject constructor() : WorkoutRepository {}

class TestWorkoutRepository() : WorkoutRepository {}

@Module
@InstallIn(ViewModelComponent::class)
abstract class WorkoutRepositoryModule {

    @Binds
    abstract fun bindWorkoutRepository(
        localWorkoutRepository: LocalWorkoutRepository
    ) : WorkoutRepository
}