package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class Dating(
    @SerializedName("period")
    val period: Int? = null,
    @SerializedName("presentingDate")
    val presentingDate: String? = null,
    @SerializedName("sortingDate")
    val sortingDate: Int? = null,
    @SerializedName("yearEarly")
    val yearEarly: Int? = null,
    @SerializedName("yearLate")
    val yearLate: Int? = null
)