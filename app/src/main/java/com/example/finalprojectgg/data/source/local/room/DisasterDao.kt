package com.example.finalprojectgg.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DisasterDao {
    @Query("SELECT * FROM reports")
    fun getAllReports(): Flow<List<ReportEntity>>

    fun insertReports(reports:List<ReportEntity>)
}