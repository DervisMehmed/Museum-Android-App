package com.example.museumapp.models.collectionModels

import java.io.Serializable

data class Image (
    val guid: String? = null,
    val offsetPercentageX: Long? = null,
    val offsetPercentageY: Long? = null,
    val width: Long? = null,
    val height: Long? = null,
    val url: String? = null
) : Serializable