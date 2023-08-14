package com.example.finalprojectgg.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "province")
data class ProvinceEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    var provinceCode: String,
    var name: String?,
    var latitude: Double?,
    var longitude:Double?
)
