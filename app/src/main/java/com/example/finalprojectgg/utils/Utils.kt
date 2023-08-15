package com.example.finalprojectgg.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import com.example.finalprojectgg.data.source.local.entity.ProvinceEntity
import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import com.example.finalprojectgg.data.source.remote.response.GeometriesItem
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.ReportModel
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng

class Utils {
    object DataMapper {

        /**
         * Map Province Model[FilterProvinceModel] to Provide Entity [ProvinceEntity]
         */
        fun provinceModelToProvinceEntity(data: FilterProvinceModel): ProvinceEntity =
            ProvinceEntity(
                provinceCode = data.id,
                name = data.name,
                latitude = data.coordinate.latitude,
                longitude = data.coordinate.longitude
            )

        /**
         * Map Provide Entity [ProvinceEntity] to Province Model[FilterProvinceModel]
         */
        fun provinceEntityToProvinceModel(data: ProvinceEntity): FilterProvinceModel =
            FilterProvinceModel(
                id = data.provinceCode,
                name = data.name ?: "",
                coordinate = LatLng(data.latitude ?: 0.0, data.longitude ?: 0.0),
                isActive = false
            )

        /**
         * Map Response API [GeometriesItem] to ReportModel[ReportModel]
         */
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
                province = properties?.tags?.instanceRegionCode ?: ""
            )
        }

        /**
         * Map Response API [GeometriesItem] to Report Entity[ReportEntity]
         */
        fun reportApiToReportEntity(data: GeometriesItem?): ReportEntity {
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
                latitude = coordinates?.get(1) ?: 0.0,
                longitude = coordinates?.get(0) ?: 0.0,
                province = properties?.tags?.instanceRegionCode ?: ""
            )
        }

        /**
         * Map Report Entity[ReportModel] to Report Model[ReportModel]
         */
        fun reportEntityToReportModel(data: ReportEntity): ReportModel {
            return ReportModel(
                id = data.id,
                title = data.title ?: "No Title",
                desc = data.desc ?: "",
                date = data.date ?: "",
                status = data.status ?: "",
                imgUrl = data.imgUrl ?: "",
                category = data.category ?: "",
                latLng = LatLng(data.latitude ?: 0.0, data.longitude ?: 0.0),
                province = data.province ?: ""
            )
        }
    }

    object Filter {
        /**
         * Filtering logic for list of [ReportModel]. This kinda tricky :) but i use only filtering online only data
         */
        fun recentReport(filterActive: FilterActive, data: List<ReportModel>): List<ReportModel> {
            val filterDisaster = filterActive.filterByDisaster.isNotEmpty()
            val filterProvince = filterActive.filterByProvince.isNotEmpty()
            return if (filterDisaster && filterProvince) {
                val temp = mutableListOf<ReportModel>()
                temp.addAll(filterByDisaster(filterActive.filterByDisaster, data))
                temp.addAll(filterByProvince(filterActive.filterByProvince, temp))
                temp.sortedByDescending {
                    it.id
                }
            } else if (filterDisaster) {
                filterByDisaster(filterActive.filterByDisaster, data)
            } else if (filterProvince) {
                filterByProvince(filterActive.filterByProvince, data)
            } else data
        }

        private fun filterByDisaster(
            queries: List<String>,
            data: List<ReportModel>
        ): List<ReportModel> {
            val temp = mutableListOf<ReportModel>()
            queries.forEach { query ->
                temp.addAll(data.filter {
                    it.category == query
                })
            }
            return temp.sortedByDescending {
                it.id
            }
        }

        private fun filterByProvince(
            queries: List<String>,
            data: List<ReportModel>
        ): List<ReportModel> {
            val temp = mutableListOf<ReportModel>()
            queries.forEach { query ->
                temp.addAll(data.filter {
                    it.province == query
                })
            }
            return temp.sortedByDescending { it.id }
        }

    }

    object Maps {
        /**
         * Used to convert resource to a Bitmap
         */
        fun bitmapDescriptor(
            context: Context,
            vectorResId: Int
        ): BitmapDescriptor? {

            // retrieve the actual drawable
            val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            val bm = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )

            // draw it onto the bitmap
            val canvas = android.graphics.Canvas(bm)
            drawable.draw(canvas)
            return BitmapDescriptorFactory.fromBitmap(bm)
        }
    }
}