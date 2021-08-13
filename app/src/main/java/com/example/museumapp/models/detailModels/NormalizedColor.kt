package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class NormalizedColor(
    @SerializedName("hex")
    val hex: String? = null,
    @SerializedName("percentage")
    val percentage: Int? = null
)