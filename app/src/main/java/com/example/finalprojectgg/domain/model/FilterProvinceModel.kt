package com.example.finalprojectgg.domain.model

import androidx.compose.runtime.mutableStateListOf
import com.google.android.gms.maps.model.LatLng

data class FilterProvinceModel(
    var id: String,
    var name: String,
    var coordinate: LatLng,
    var isActive: Boolean = false
)

val listProvince = mutableStateListOf(
    FilterProvinceModel(
        id = "ID-AC",
        name = "Aceh",
        coordinate = LatLng(4.650497, 96.739756)
    ),
    FilterProvinceModel(
        id = "ID-MU",
        name = "Maluku Utara",
        coordinate = LatLng(1.504817, 127.791750)
    ),
    FilterProvinceModel(
        id = "ID-BA",
        name = "Bali",
        coordinate = LatLng(-8.424623, 115.189791)
    ),
    FilterProvinceModel(
        id = "ID-SA",
        name = "Sulawesi Utara",
        coordinate = LatLng(0.596724, 123.963783)
    ),
    FilterProvinceModel(
        id = "ID-BB",
        name = "Kep Bangka Belitung",
        coordinate = LatLng(-2.792918, 106.441667)
    ),
    FilterProvinceModel(
        id = "ID-SU",
        name = "Sumatera Utara",
        coordinate = LatLng(2.056470, 99.577713)
    ),
    FilterProvinceModel(
        id = "ID-BT",
        name = "Banten",
        coordinate = LatLng(-6.471673, 106.065380)
    ),
    FilterProvinceModel(
        id = "ID-PA",
        name = "Papua",
        coordinate = LatLng(-4.524827, 138.029395)
    ),
    FilterProvinceModel(
        id = "ID-BE",
        name = "Bengkulu",
        coordinate = LatLng(-3.797937, 102.271273)
    ),
    FilterProvinceModel(
        id = "ID-RI",
        name = "Riau",
        coordinate = LatLng(0.106502, 101.694888)
    ),
    FilterProvinceModel(
        id = "ID-JT",
        name = "Jawa Tengah",
        coordinate = LatLng(-7.198461, 110.165830)
    ),
    FilterProvinceModel(
        id = "ID-KR",
        name = "Kepulauan Riau",
        coordinate = LatLng(0.400817, 104.403555)
    ),
    FilterProvinceModel(
        id = "ID-KT",
        name = "Kalimantan Tengah",
        coordinate = LatLng(-1.759343, 113.344462)
    ),
    FilterProvinceModel(
        id = "ID-SG",
        name = "Sulawesi Tenggara",
        coordinate = LatLng(-4.216541, 122.158155)
    ),
    FilterProvinceModel(
        id = "ID-ST",
        name = "Sulawesi Tengah",
        coordinate = LatLng(-1.522532, 121.423141)
    ),
    FilterProvinceModel(
        id = "ID-KS",
        name = "Kalimantan Selatan",
        coordinate = LatLng(-3.140283, 115.262148)
    ),
    FilterProvinceModel(
        id = "ID-JI",
        name = "Jawa Timur",
        coordinate = LatLng(-7.596598, 112.229402)
    ),
    FilterProvinceModel(
        id = "ID-SN",
        name = "Sulawesi Selatan",
        coordinate = LatLng(-3.753792, 119.959720)
    ),
    FilterProvinceModel(
        id = "ID-KI",
        name = "Kalimantan Timur",
        coordinate = LatLng(0.461679, 116.443903)
    ),
    FilterProvinceModel(
        id = "ID-SS",
        name = "Sumatera Selatan",
        coordinate = LatLng(-3.391877, 103.849363)
    ),
    FilterProvinceModel(
        id = "ID-NT",
        name = "Nusa Tenggara Timur",
        coordinate = LatLng(-8.702926, 121.083786)
    ),
    FilterProvinceModel(
        id = "ID-YO",
        name = "DI Yogyakarta",
        coordinate = LatLng(-7.886925, 110.425692)
    ),
    FilterProvinceModel(
        id = "ID-GO",
        name = "Gorontalo",
        coordinate = LatLng(0.541524, 123.056237)
    ),
    FilterProvinceModel(
        id = "ID-JB",
        name = "Jawa Barat",
        coordinate = LatLng(-7.107467, 107.663342)
    ),
    FilterProvinceModel(
        id = "ID-JK",
        name = "DKI Jakarta",
        coordinate = LatLng(-6.212913, 106.849845)
    ),
    FilterProvinceModel(
        id = "ID-KB",
        name = "Kalimantan Barat",
        coordinate = LatLng(-0.347132, 111.458496)
    ),
    FilterProvinceModel(
        id = "ID-JA",
        name = "Jambi",
        coordinate = LatLng(-1.617293, 103.613634)
    ),
    FilterProvinceModel(
        id = "ID-NB",
        name = "Nusa Tenggara Barat",
        coordinate = LatLng(-8.695352, 117.358391)
    ),
    FilterProvinceModel(
        id = "ID-LA",
        name = "Lampung",
        coordinate = LatLng(-4.583191, 105.395466)
    ),
    FilterProvinceModel(
        id = "ID-PB",
        name = "Papua Barat",
        coordinate = LatLng(-1.382023, 133.155960)
    ),
    FilterProvinceModel(
        id = "ID-MA",
        name = "Maluku",
        coordinate = LatLng(
            -3.253659, 130.141172
        )
    ),
    FilterProvinceModel(
        id = "ID-SR",
        name = "Sulawesi Barat",
        coordinate = LatLng(-2.863340, 119.226967)
    ),
    FilterProvinceModel(
        id = "ID-KU",
        name = "Kalimantan Utara",
        coordinate = LatLng(3.053674, 116.030391)
    ),
    FilterProvinceModel(
        id = "ID-SB",
        name = "Sumatera Barat",
        coordinate = LatLng(-0.754656, 100.798169)
    )
)
