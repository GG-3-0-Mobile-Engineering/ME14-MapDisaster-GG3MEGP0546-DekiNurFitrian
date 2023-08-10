package com.example.finalprojectgg.domain.model


import androidx.annotation.DrawableRes
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.finalprojectgg.R

data class ChipModel(
    var selected: Boolean = false,
    var title: String,
    @DrawableRes val icon: Int
)

val listDisaster = listOf(
    ChipModel(
        title = "Banjir",
        icon = R.drawable.ic_flood
    ),
    ChipModel(
        title = "Gempabumi",
        icon = R.drawable.ic_earthquake
    ),
    ChipModel(
        title = "Angin Kencang",
        icon = R.drawable.ic_air
    ),
    ChipModel(
        title = "Kabut Asap",
        icon = R.drawable.ic_haze
    ),
    ChipModel(
        title = "Kebakaran Hutan",
        icon = R.drawable.ic_fire
    ),
    ChipModel(
        title = "Gunung Api",
        icon = R.drawable.ic_volcano
    ),
)

val listProvince = arrayListOf(
    ChipModel(
        title = "Aceh",
        icon = R.drawable.ic_volcano
    ),
    ChipModel(
        title = "Jawa Tengah",
        icon = R.drawable.ic_volcano
    ),
    ChipModel(
        title = "Jawa Timur",
        icon = R.drawable.ic_volcano
    ),
    ChipModel(
        title = "Jawa Barat",
        icon = R.drawable.ic_volcano
    ),
    ChipModel(
        title = "Jakarta",
        icon = R.drawable.ic_volcano
    ),
    ChipModel(
        title = "Yogyakarta",
        icon = R.drawable.ic_volcano
    ),
)