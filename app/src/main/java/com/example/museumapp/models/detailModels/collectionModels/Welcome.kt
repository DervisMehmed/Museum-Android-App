package com.example.museumapp.models.detailModels.collectionModels

import java.io.Serializable

data class Welcome (
    val elapsedMilliseconds: Long? = null,
    val count: Long? = null,
    val countFacets: CountFacets? = null,
    val artObjects: List<ArtObject>? = null,
    val facets: List<WelcomeFacet>? = null
) : Serializable