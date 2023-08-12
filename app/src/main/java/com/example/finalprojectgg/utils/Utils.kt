package com.example.finalprojectgg.utils

import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import com.example.finalprojectgg.data.source.remote.response.GeometriesItem
import com.example.finalprojectgg.domain.model.ReportModel
import com.google.android.gms.maps.model.LatLng

class Utils {
    object DataMapper {
        fun reportApiToReportModel(data: GeometriesItem?): ReportModel {
            val properties = data?.properties
            val coordinates = data?.coordinates
            return ReportModel(
                id = properties?.pkey?.toInt() ?: 0,
                title = properties?.title ?: "",
                desc = properties?.text ?: "",
                date = properties?.createdAt ?: "",
                status = properties?.status ?: "",
                imgUrl = properties?.imageUrl ?: "",
                category = properties?.disasterType ?: "",
                latLng = coordinates.let {
                    LatLng(it?.get(0) ?: 0.0, it?.get(1) ?: 0.0)
                },
                province = properties?.tags?.regionCode ?: ""
            )
        }

        fun reportApiToReportEntity(data:GeometriesItem?):ReportEntity{
            val properties = data?.properties
            val coordinates = data?.coordinates
            return ReportEntity(
                id = properties?.pkey?.toInt() ?: 0,
                title = properties?.title ?: "",
                desc = properties?.text ?: "",
                date = properties?.createdAt ?: "",
                status = properties?.status ?: "",
                imgUrl = properties?.imageUrl ?: "",
                category = properties?.disasterType ?: "",
                latitude = coordinates?.get(0) ?: 0.0,
                longitude = coordinates?.get(1) ?: 0.0,
                province = properties?.tags?.regionCode ?: ""
            )
        }

        fun reportEntityToReportModel(data:ReportEntity):ReportModel{
            return ReportModel(
                id = data.id,
                title = data.title ?: "No Title",
                desc = data.desc ?: "",
                date = data.date ?: "",
                status = data.status ?: "",
                imgUrl = data.imgUrl ?: "",
                category = data.category ?: "",
                latLng = LatLng(data.latitude ?: 0.0,data.longitude ?: 0.0),
                province = data.province ?: ""
            )
        }
    }
}