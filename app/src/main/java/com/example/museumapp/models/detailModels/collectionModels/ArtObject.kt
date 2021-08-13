package com.example.museumapp.models.detailModels.collectionModels

import java.io.Serializable

data class ArtObject (
    val links: Links? = null,
    val id: String? = null,
    val objectNumber: String? = null,
    val title: String? = null,
    val hasImage: Boolean? = null,
    val principalOrFirstMaker: String? = null,
    val longTitle: String? = null,
    val showImage: Boolean? = null,
    val permitDownload: Boolean? = null,
    val webImage: Image? = null,
    val headerImage: Image? = null,
    val productionPlaces: List<String>? = null
)  : Serializable{

}