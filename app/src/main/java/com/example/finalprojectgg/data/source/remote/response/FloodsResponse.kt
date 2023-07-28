package com.example.finalprojectgg.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class FloodsResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)