package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class PrincipalMaker(
    @SerializedName("biography")
    val biography: Any? = null,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String? = null,
    @SerializedName("dateOfBirthPrecision")
    val dateOfBirthPrecision: Any? = null,
    @SerializedName("dateOfDeath")
    val dateOfDeath: String? = null,
    @SerializedName("dateOfDeathPrecision")
    val dateOfDeathPrecision: Any? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("nationality")
    val nationality: Any? = null,
    @SerializedName("occupation")
    val occupation: List<String>? = null,
    @SerializedName("placeOfBirth")
    val placeOfBirth: String? = null,
    @SerializedName("placeOfDeath")
    val placeOfDeath: String? = null,
    @SerializedName("productionPlaces")
    val productionPlaces: List<String>? = null,
    @SerializedName("qualification")
    val qualification: Any? = null,
    @SerializedName("roles")
    val roles: List<String>? = null,
    @SerializedName("unFixedName")
    val unFixedName: String? = null
)