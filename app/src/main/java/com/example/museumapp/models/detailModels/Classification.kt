package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class Classification(
    @SerializedName("events")
    val events: List<Any>? = null,
    @SerializedName("iconClassDescription")
    val iconClassDescription: List<String>? = null,
    @SerializedName("iconClassIdentifier")
    val iconClassIdentifier: List<String>? = null,
    @SerializedName("motifs")
    val motifs: List<Any>? = null,
    @SerializedName("objectNumbers")
    val objectNumbers: List<String>? = null,
    @SerializedName("people")
    val people: List<String>? = null,
    @SerializedName("periods")
    val periods: List<Any>? = null,
    @SerializedName("places")
    val places: List<String>? = null
)