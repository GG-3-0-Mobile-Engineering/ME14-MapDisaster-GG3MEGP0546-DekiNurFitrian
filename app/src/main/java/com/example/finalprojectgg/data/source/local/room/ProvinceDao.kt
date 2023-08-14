package com.example.finalprojectgg.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectgg.data.source.local.entity.ProvinceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProvinceDao {
    @Insert(entity = ProvinceEntity::class, onConflict = OnConflictStrategy.ABORT)
    fun insertAllProvince(provinces:List<ProvinceEntity>)
    @Query("SELECT * FROM province")
    fun getAllProvince(): Flow<List<ProvinceEntity>>
    @Query("SELECT * FROM province WHERE name LIKE :query LIMIT 8")
    fun getProvincesByName(query: String):Flow<List<ProvinceEntity>>
}