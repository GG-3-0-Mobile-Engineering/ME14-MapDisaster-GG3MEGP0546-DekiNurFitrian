package com.example.finalprojectgg.data.source.remote.network

import com.example.finalprojectgg.data.source.remote.response.FloodgaugesResponse
import com.example.finalprojectgg.data.source.remote.response.FloodsResponse
import com.example.finalprojectgg.data.source.remote.response.ReportsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PetaBencanaApiService {
    @GET("reports")
    suspend fun getReports(
        @Query("admin") admin: String? = null,
        @Query("format") format: String? = null,
        @Query("geoformat") geoFormat: String? = null,
        @Query("disaster") disaster: String? = null,
        @Query("timeperiod") timePeriod: Int? = null,
    ): ReportsResponse

    @GET("floods")
    suspend fun getFloods(
        @Query("admin") admin: String,
        @Query("format") format: String,
        @Query("geoformat") geoFormat: String,
        @Query("minimum_state") minimumState: Int,
    ): FloodsResponse

    @GET("floodgauges")
    suspend fun getFloodgauges(
        @Query("admin") admin: String,
        @Query("format") format: String,
        @Query("geoformat") geoFormat: String,
    ): FloodgaugesResponse
}