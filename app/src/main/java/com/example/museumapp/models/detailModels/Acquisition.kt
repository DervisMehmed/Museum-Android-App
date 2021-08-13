package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class Acquisition(
    @SerializedName("creditLine")
    val creditLine: String? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("method")
    val method: String? = null
)