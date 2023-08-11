package com.example.finalprojectgg.domain.model

import androidx.compose.runtime.mutableStateListOf

data class FilterProvinceModel(
    var id:String,
    var name:String,
    var isActive:Boolean = false
)

val listFilterProvince = mutableStateListOf(
    FilterProvinceModel(
        id = "ID-AC",
        name = "Aceh"
    ),
    FilterProvinceModel(
        id = "ID-BA",
        name = "Bali"
    ),
    FilterProvinceModel(
        id = "ID-BB",
        name = "Kep Bangka Belitung"
    ),
    FilterProvinceModel(
        id = "ID-BT",
        name = "Banten"
    ),
    FilterProvinceModel(
        id = "ID-BE",
        name = "Bengkulu"
    ),
    FilterProvinceModel(
        id = "ID-JT",
        name = "Jawa Tengah"
    ),
    FilterProvinceModel(
        id = "ID-KT",
        name = "Kalimantan Tengah"
    )
)