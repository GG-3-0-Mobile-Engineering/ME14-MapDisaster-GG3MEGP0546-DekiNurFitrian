package com.example.finalprojectgg.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReportData(

	@field:SerializedName("flood_depth")
	val floodDepth: Int? = null,

	@field:SerializedName("report_type")
	val reportType: String? = null
)