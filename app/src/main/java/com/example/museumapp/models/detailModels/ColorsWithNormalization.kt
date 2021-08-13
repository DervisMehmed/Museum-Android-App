package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class ColorsWithNormalization(
    @SerializedName("normalizedHex")
    val normalizedHex: String? = null,
    @SerializedName("originalHex")
    val originalHex: String? = null
)