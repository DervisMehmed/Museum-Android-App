package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class ArtObjectPage(
    @SerializedName("adlibOverrides")
    val adlibOverrides: AdlibOverrides? = null,
    @SerializedName("audioFile1")
    val audioFile1: Any? = null,
    @SerializedName("audioFileLabel1")
    val audioFileLabel1: Any? = null,
    @SerializedName("audioFileLabel2")
    val audioFileLabel2: Any? = null,
    @SerializedName("createdOn")
    val createdOn: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("lang")
    val lang: String? = null,
    @SerializedName("objectNumber")
    val objectNumber: String? = null,
    @SerializedName("plaqueDescription")
    val plaqueDescription: String? = null,
    @SerializedName("similarPages")
    val similarPages: List<Any>? = null,
    @SerializedName("tags")
    val tags: List<Any>? = null,
    @SerializedName("updatedOn")
    val updatedOn: String? = null
)