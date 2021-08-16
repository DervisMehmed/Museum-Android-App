package com.example.museumapp.models.detailModels

import com.google.gson.JsonElement

data class Classification(
    val iconClassIdentifier: List<String>? = null,
    val iconClassDescription: List<String>? = null,
    val motifs: JsonElement? = null,
    val events: JsonElement? = null,
    val periods: JsonElement? = null,
    val places: List<String>? = null,
    val people: List<String>? = null,
    val objectNumbers: List<String>? = null
)
