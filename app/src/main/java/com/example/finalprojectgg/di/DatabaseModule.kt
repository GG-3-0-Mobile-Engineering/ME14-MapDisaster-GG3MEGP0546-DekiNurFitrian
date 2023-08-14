package com.example.finalprojectgg.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.finalprojectgg.data.source.local.room.DisasterDao
import com.example.finalprojectgg.data.source.local.room.DisasterDatabase
import com.example.finalprojectgg.data.source.local.room.ProvinceCallback
import com.example.finalprojectgg.data.source.local.room.ProvinceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context,provider: Provider<ProvinceDao>): DisasterDatabase =
        Room.databaseBuilder(
            context,
            DisasterDatabase::class.java, "disaster.db"
        )
            .fallbackToDestructiveMigration()
            .addCallback(ProvinceCallback(provider))
            .build()

    @Provides
    fun provideDatabaseDao(database: DisasterDatabase): DisasterDao = database.disasterDao()

    @Provides
    fun provideProvinceDao(database:DisasterDatabase): ProvinceDao = database.provinceDao()
}