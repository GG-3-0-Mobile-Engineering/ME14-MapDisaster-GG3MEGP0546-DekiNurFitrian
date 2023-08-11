package com.example.finalprojectgg.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GeometriesItem(

	@field:SerializedName("coordinates")
	val coordinates: List<Double>? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("properties")
	val properties: Properties? = null
)