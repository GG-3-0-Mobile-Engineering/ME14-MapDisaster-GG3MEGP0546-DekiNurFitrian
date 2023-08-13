package com.example.finalprojectgg.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DisasterDao {
    @Transaction
    @Query(
        value = """
            SELECT * FROM reports
            WHERE 
                CASE WHEN :useFilterDisaster
                    THEN category IN (:filterDisaster)
                    ELSE 1
                END
             AND
                CASE WHEN :useFilterProvince
                    THEN province IN (:filterProvince)
                    ELSE 1
                END
    """,
    )
    fun getAllReports(
        useFilterDisaster: Boolean = false,
        filterDisaster:List<String> = listOf(),
        useFilterProvince:Boolean = false,
        filterProvince:List<String> = listOf()
    ): Flow<List<ReportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ReportEntity::class)
    fun insertReports(reports: List<ReportEntity>)
}