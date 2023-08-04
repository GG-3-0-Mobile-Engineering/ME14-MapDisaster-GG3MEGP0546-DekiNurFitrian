package com.example.finalprojectgg.domain.model

import com.google.android.gms.maps.model.LatLng

data class Report(
    var title:String,
    var desc:String,
    var date:String,
    var status:String,
    var imgUrl:String,
    var category: String,
    var latLng: LatLng,
    var province:String
)

val listReportDummy = listOf(
    Report(
        title = "Banjir Lagi",
        desc = "Banjir di sekitaran Semarang Tawang,Banjir di sekitaran Semarang Tawang,Banjir di sekitaran Semarang Tawang,Banjir di sekitaran Semarang TawangBanjir di sekitaran Semarang TawangBanjir di sekitaran Semarang TawangBanjir di sekitaran Semarang Tawang",
        date = "2023-27-07T18:03:33:00.176Z",
        status = "confirmed",
        imgUrl = "https://media-cldnry.s-nbcnews.com/image/upload/rockcms/2023-07/230714-vermont-flooding-mjf-1110-55ab42.jpg",
        category = "Banjir",
        latLng = LatLng(-6.961213, 110.422580),
        province = "Jawa Tengah"
    ),
    Report(
        title = "Gempa Lurr",
        desc = "Kebakaran di sekitaran Semarang Tawang",
        date = "2023-27-07T18:03:33:00.176Z",
        status = "confirmed",
        imgUrl = "https://media-cldnry.s-nbcnews.com/image/upload/rockcms/2023-07/230714-vermont-flooding-mjf-1110-55ab42.jpg",
        category = "Gempabumi",
        latLng = LatLng(-7.006032, 110.413840),
        province = "Jawa Tengah"
    ),
    Report(
        title = "Angin Lagi",
        desc = "Banjir di sekitaran Semarang Tawang",
        date = "2023-27-07T18:03:33:00.176Z",
        status = "confirmed",
        imgUrl = "https://media-cldnry.s-nbcnews.com/image/upload/rockcms/2023-07/230714-vermont-flooding-mjf-1110-55ab42.jpg",
        category = "Angin Kencang",
        latLng = LatLng(-6.997840, 110.391991),
        province = "Jawa Tengah"
    ),
    Report(
        title = "Kabut Lagi",
        desc = "Kabut di sekitaran Semarang Tawang",
        date = "2023-27-07T18:03:33:00.176Z",
        status = "confirmed",
        imgUrl = "https://media-cldnry.s-nbcnews.com/image/upload/rockcms/2023-07/230714-vermont-flooding-mjf-1110-55ab42.jpg",
        category = "Kabut Asap",
        latLng = LatLng(-6.903520, 107.661598),
        province = "Jawa Barat"
    ),
    Report(
        title = "Kebakaran Hutan Lurr",
        desc = "Kebakaran di sekitaran Semarang Tawang",
        date = "2023-27-07T18:03:33:00.176Z",
        status = "confirmed",
        imgUrl = "https://media-cldnry.s-nbcnews.com/image/upload/rockcms/2023-07/230714-vermont-flooding-mjf-1110-55ab42.jpg",
        category = "Kebakaran Hutan",
        latLng = LatLng(0.518820, 116.194610),
        province = "Kalimantan Barat"
    ),
    Report(
        title = "Gunung Api meleutus",
        desc = "Banjir di sekitaran Semarang Tawang",
        date = "2023-27-07T18:03:33:00.176Z",
        status = "confirmed",
        imgUrl = "https://media-cldnry.s-nbcnews.com/image/upload/rockcms/2023-07/230714-vermont-flooding-mjf-1110-55ab42.jpg",
        category = "Gunung Api",
        latLng = LatLng(-8.040564, 113.014086),
        province = "Jawa Timur"
    ),
)
