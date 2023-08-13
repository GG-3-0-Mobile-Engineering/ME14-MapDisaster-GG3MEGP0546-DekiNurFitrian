package com.example.finalprojectgg.data.source.local

import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import com.example.finalprojectgg.data.source.local.room.DisasterDao
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.ui.screens.state.FilterState
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val disasterDao: DisasterDao) {
    fun getAllReports(
        filterQuery:FilterActive
    ) = disasterDao.getAllReports(
        useFilterDisaster = filterQuery.filterByDisaster.isNotEmpty(),
        filterDisaster = filterQuery.filterByDisaster,
        useFilterProvince = filterQuery.filterByProvince.isNotEmpty(),
        filterProvince = filterQuery.filterByProvince
    )
    suspend fun insertReports(reports: List<ReportEntity>) = disasterDao.insertReports(reports)
}