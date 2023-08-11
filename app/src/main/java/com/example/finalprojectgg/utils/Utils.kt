package com.example.finalprojectgg.utils

import com.example.finalprojectgg.data.source.remote.response.GeometriesItem
import com.example.finalprojectgg.domain.model.Report
import com.google.android.gms.maps.model.LatLng

class Utils {
    object DataMapper {
        fun reportApiToReportModel(data: GeometriesItem?): Report {
            val properties = data?.properties
            val coordinates = data?.coordinates
            return Report(
                id = properties?.pkey ?: "",
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
    }
}