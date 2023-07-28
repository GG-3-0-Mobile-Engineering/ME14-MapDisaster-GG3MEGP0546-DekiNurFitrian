package com.example.finalprojectgg.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Output(

	@field:SerializedName("geometries")
	val geometries: List<GeometriesItem?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)