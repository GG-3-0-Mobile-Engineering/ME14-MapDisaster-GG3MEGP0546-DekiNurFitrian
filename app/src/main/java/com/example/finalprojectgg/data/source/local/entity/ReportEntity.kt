package com.example.finalprojectgg.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reports")
data class ReportEntity(
    @PrimaryKey
    val id:Int,
    var title:String?,
    var desc:String?,
    var date:String?,
    var status:String?,
    var imgUrl:String?,
    var category: String?,
    val latitude:Long?,
    val longitude:Long?,
    var province:String?
)
