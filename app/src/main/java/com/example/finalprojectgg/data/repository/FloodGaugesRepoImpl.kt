package com.example.finalprojectgg.data.repository

import com.example.finalprojectgg.data.source.local.LocalDataSource
import com.example.finalprojectgg.data.source.remote.RemoteDataSource
import com.example.finalprojectgg.domain.repository.FloodGaugesRepository
import javax.inject.Inject

class FloodGaugesRepoImpl@Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :FloodGaugesRepository {
    override fun updateWaterLevel() {
        TODO("Not yet implemented")
    }
}