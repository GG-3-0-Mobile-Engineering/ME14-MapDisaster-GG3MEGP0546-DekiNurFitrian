package com.example.finalprojectgg.domain.model


import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.finalprojectgg.R

data class ChipModel(
    var selected:Boolean = false,
    var title: String,
    var icon: @Composable () -> Unit
)

val listDisaster = arrayListOf(
    ChipModel(
        title = "Banjir",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Gempabumi",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Angin Kencang",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Kabut Asap",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Kebakaran Hutan",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Gunung Api",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
)

val listProvince = arrayListOf(
    ChipModel(
        title = "Aceh",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Jawa Tengah",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Jawa Timur",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Jawa Barat",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Jakarta",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
    ChipModel(
        title = "Yogyakarta",
        icon = {
            Icon(painter = painterResource(id = R.drawable.img_flood) , contentDescription = null )
        }
    ),
)