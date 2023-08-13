package com.example.finalprojectgg.domain.model


import androidx.annotation.DrawableRes
import com.example.finalprojectgg.R

data class FilterDisasterModel(
    var id:String,
    var title: String,
    var selected: Boolean = false,
    @DrawableRes val icon: Int
)

val listDisaster = listOf(
    FilterDisasterModel(
        id = "flood",
        title = "Banjir",
        icon = R.drawable.ic_flood
    ),
    FilterDisasterModel(
        id = "earthquake",
        title = "Gempabumi",
        icon = R.drawable.ic_earthquake
    ),
    FilterDisasterModel(
        id = "wind",
        title = "Angin Kencang",
        icon = R.drawable.ic_air
    ),
    FilterDisasterModel(
        id = "haze",
        title = "Kabut Asap",
        icon = R.drawable.ic_haze
    ),
    FilterDisasterModel(
        id = "fire",
        title = "Kebakaran Hutan",
        icon = R.drawable.ic_fire
    ),
    FilterDisasterModel(
        id = "volcano",
        title = "Gunung Api",
        icon = R.drawable.ic_volcano
    ),
)