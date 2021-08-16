package com.example.museumapp.models.detailModels

import com.google.gson.JsonElement

data class ArtObjectPage(
    val id: String? = null,
    val similarPages: JsonElement? = null,
    val lang: String? = null,
    val objectNumber: String? = null,
    val tags: JsonElement? = null,
    val plaqueDescription: String? = null,
    val audioFile1: JsonElement? = null,
    val audioFileLabel1: JsonElement? = null,
    val audioFileLabel2: JsonElement? = null,
    val createdOn: String? = null,
    val updatedOn: String? = null,
    val adlibOverrides: AdlibOverrides? = null
)
