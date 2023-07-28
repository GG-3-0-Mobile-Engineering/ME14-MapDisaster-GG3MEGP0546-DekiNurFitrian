package com.example.finalprojectgg.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext context: Context): MapDisasterDatabase = Room.databaseBuilder(
//        context,
//        MapDisasterDatabase::class.java, "game.db"
//    ).fallbackToDestructiveMigration()
//        .build()
//
//    @Provides
//    fun provideGameDao(database: GameDatabase): GameDao = database.gameDao()
}