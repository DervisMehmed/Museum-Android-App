package com.example.museumapp.models.detailModels


import com.google.gson.annotations.SerializedName

data class ArtObject2(
    @SerializedName("acquisition")
    val acquisition: Acquisition? = null,
    @SerializedName("artistRole")
    val artistRole: Any? = null,
    @SerializedName("associations")
    val associations: List<Any>? = null,
    @SerializedName("catRefRPK")
    val catRefRPK: List<Any>? = null,
    @SerializedName("classification")
    val classification: Classification? = null,
    @SerializedName("colors")
    val colors: List<Color>? = null,
    @SerializedName("colorsWithNormalization")
    val colorsWithNormalization: List<ColorsWithNormalization>? = null,
    @SerializedName("copyrightHolder")
    val copyrightHolder: Any? = null,
    @SerializedName("dating")
    val dating: Dating? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("dimensions")
    val dimensions: List<Dimension>? = null,
    @SerializedName("documentation")
    val documentation: List<String>? = null,
    @SerializedName("exhibitions")
    val exhibitions: List<Any>? = null,
    @SerializedName("hasImage")
    val hasImage: Boolean? = null,
    @SerializedName("historicalPersons")
    val historicalPersons: List<String>? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("inscriptions")
    val inscriptions: List<Any>? = null,
    @SerializedName("label")
    val label: Label? = null,
    @SerializedName("labelText")
    val labelText: Any? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("links")
    val links2: Links2? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("longTitle")
    val longTitle: String? = null,
    @SerializedName("makers")
    val makers: List<Any>? = null,
    @SerializedName("materials")
    val materials: List<String>? = null,
    @SerializedName("normalized32Colors")
    val normalized32Colors: List<Normalized32Color>? = null,
    @SerializedName("normalizedColors")
    val normalizedColors: List<NormalizedColor>? = null,
    @SerializedName("objectCollection")
    val objectCollection: List<String>? = null,
    @SerializedName("objectNumber")
    val objectNumber: String? = null,
    @SerializedName("objectTypes")
    val objectTypes: List<String>? = null,
    @SerializedName("physicalMedium")
    val physicalMedium: String? = null,
    @SerializedName("physicalProperties")
    val physicalProperties: List<Any>? = null,
    @SerializedName("plaqueDescriptionDutch")
    val plaqueDescriptionDutch: String? = null,
    @SerializedName("plaqueDescriptionEnglish")
    val plaqueDescriptionEnglish: String? = null,
    @SerializedName("principalMaker")
    val principalMaker: String? = null,
    @SerializedName("principalMakers")
    val principalMakers: List<PrincipalMaker>? = null,
    @SerializedName("principalOrFirstMaker")
    val principalOrFirstMaker: String? = null,
    @SerializedName("priref")
    val priref: String? = null,
    @SerializedName("productionPlaces")
    val productionPlaces: List<String>? = null,
    @SerializedName("scLabelLine")
    val scLabelLine: String? = null,
    @SerializedName("showImage")
    val showImage: Boolean? = null,
    @SerializedName("subTitle")
    val subTitle: String? = null,
    @SerializedName("techniques")
    val techniques: List<Any>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("titles")
    val titles: List<String>? = null,
    @SerializedName("webImage")
    val webImage: WebImage? = null
)