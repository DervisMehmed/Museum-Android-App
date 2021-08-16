package com.example.museumapp.models.detailModels

import com.google.gson.JsonElement

data class Dimension(
    val unit: String? = null,
    val type: String? = null,
    val part: JsonElement? = null,
    val value: String? = null
)
