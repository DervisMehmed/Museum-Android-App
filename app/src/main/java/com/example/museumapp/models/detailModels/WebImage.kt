package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class WebImage(
    @SerializedName("guid")
    val guid: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("offsetPercentageX")
    val offsetPercentageX: Int? = null,
    @SerializedName("offsetPercentageY")
    val offsetPercentageY: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null
)