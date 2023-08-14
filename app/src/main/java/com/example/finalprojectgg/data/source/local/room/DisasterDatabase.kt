package com.example.finalprojectgg.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalprojectgg.data.source.local.entity.ProvinceEntity
import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import javax.inject.Singleton

@Database(
    entities = [ReportEntity::class,ProvinceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DisasterDatabase : RoomDatabase() {
    abstract fun disasterDao(): DisasterDao
    abstract fun provinceDao(): ProvinceDao
}