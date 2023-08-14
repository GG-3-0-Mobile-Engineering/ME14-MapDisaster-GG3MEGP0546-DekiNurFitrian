package com.example.finalprojectgg.domain.model

import com.google.android.gms.maps.model.LatLng

data class ReportModel(
    var id: Int = 0,
    var title:String,
    var desc:String,
    var date:String,
    var status:String,
    var imgUrl:String,
    var category: String,
    var latLng: LatLng,
    var province:String
)

