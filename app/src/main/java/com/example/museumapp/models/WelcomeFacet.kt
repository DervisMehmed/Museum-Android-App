package com.example.museumapp.models

import java.io.Serializable

data class WelcomeFacet (
    val facets: List<FacetFacet>? = null,
    val name: String? = null,
    val otherTerms: Long? = null,
    val prettyName: Long? = null
) : Serializable