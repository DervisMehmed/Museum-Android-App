package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class Dimension(
    @SerializedName("part")
    val part: Any? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("unit")
    val unit: String? = null,
    @SerializedName("value")
    val value: String? = null
)