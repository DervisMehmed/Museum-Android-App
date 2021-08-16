package com.example.museumapp.models.detailModels

import com.google.gson.JsonElement

data class PrincipalMaker(
    val name: String? = null,
    val unFixedName: String? = null,
    val placeOfBirth: String? = null,
    val dateOfBirth: String? = null,
    val dateOfBirthPrecision: JsonElement? = null,
    val dateOfDeath: String? = null,
    val dateOfDeathPrecision: JsonElement? = null,
    val placeOfDeath: String? = null,
    val occupation: List<String>? = null,
    val roles: List<String>? = null,
    val nationality: JsonElement? = null,
    val biography: JsonElement? = null,
    val productionPlaces: List<String>? = null,
    val qualification: JsonElement? = null
)
