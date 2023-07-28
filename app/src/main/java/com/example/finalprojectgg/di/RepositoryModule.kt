package com.example.finalprojectgg.di

import com.example.finalprojectgg.data.repository.MapDisasterRepositoryImpl
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(gameRepository: MapDisasterRepositoryImpl): MapDisasterRepository
}