package com.example.finalprojectgg.di

import com.example.finalprojectgg.domain.usecase.MapDisasterUseCase
import com.example.finalprojectgg.domain.usecase.MapDisasterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun providesMapDisasterUseCase(useCase:MapDisasterUseCaseImpl):MapDisasterUseCase

}