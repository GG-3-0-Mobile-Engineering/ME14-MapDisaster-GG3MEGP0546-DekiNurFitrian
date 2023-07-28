package com.example.finalprojectgg.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Transform(

	@field:SerializedName("scale")
	val scale: List<Any?>? = null,

	@field:SerializedName("translate")
	val translate: List<Any?>? = null
)