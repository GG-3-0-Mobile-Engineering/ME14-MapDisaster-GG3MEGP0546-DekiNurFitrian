package com.example.finalprojectgg.data.source.local

import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import com.example.finalprojectgg.data.source.local.room.DisasterDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val disasterDao: DisasterDao) {
    fun getAllReports() = disasterDao.getAllReports()
    suspend fun insertReports(reports: List<ReportEntity>) = disasterDao.insertReports(reports)
}