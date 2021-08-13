package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("hex")
    val hex: String? = null,
    @SerializedName("percentage")
    val percentage: Int? = null
)