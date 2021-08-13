package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class Label(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("makerLine")
    val makerLine: String? = null,
    @SerializedName("notes")
    val notes: String? = null,
    @SerializedName("title")
    val title: String? = null
)