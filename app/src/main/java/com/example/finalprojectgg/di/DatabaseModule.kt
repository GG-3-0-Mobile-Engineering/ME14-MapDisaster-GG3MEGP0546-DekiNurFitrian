package com.example.finalprojectgg.di

import android.content.Context
import androidx.room.Room
import com.example.finalprojectgg.data.source.local.room.DisasterDao
import com.example.finalprojectgg.data.source.local.room.DisasterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DisasterDatabase = Room.databaseBuilder(
        context,
        DisasterDatabase::class.java, "game.db"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideGameDao(database: DisasterDatabase): DisasterDao = database.disasterDao()
}