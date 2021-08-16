package com.example.museumapp.models.detailModels

import com.google.gson.annotations.SerializedName

data class DetailWelcome(
    val elapsedMilliseconds: Long? = null,
    @SerializedName("artObject") val artObject: DetailArtObject? = null,
    val artObjectPage: ArtObjectPage? = null
)
