package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class DetailObject(
    @SerializedName("artObject")
    val artObject2: ArtObject2? = null,
    @SerializedName("artObjectPage")
    val artObjectPage: ArtObjectPage? = null,
    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds: Int? = null
)