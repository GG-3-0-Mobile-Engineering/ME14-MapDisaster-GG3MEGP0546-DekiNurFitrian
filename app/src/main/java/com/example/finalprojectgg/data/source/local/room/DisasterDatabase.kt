package com.example.finalprojectgg.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [ReportEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DisasterDatabase : RoomDatabase() {
    abstract fun disasterDao(): DisasterDao
}