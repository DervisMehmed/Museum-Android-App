package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class AdlibOverrides(
    @SerializedName("etiketText")
    val etiketText: Any? = null,
    @SerializedName("maker")
    val maker: Any? = null,
    @SerializedName("titel")
    val titel: Any? = null
)