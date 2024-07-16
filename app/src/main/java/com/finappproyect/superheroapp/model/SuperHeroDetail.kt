package com.finappproyect.superheroapp.model

import com.google.gson.annotations.SerializedName


data class SuperHeroDetail(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerStats: DetailPowerStats,
    @SerializedName("biography") val biography: DetailBiography,
    @SerializedName("image") val image : imageUrl
)

data class DetailPowerStats(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class DetailBiography(
    @SerializedName("full-name")val fullName : String,
    @SerializedName("alignment") val alignment: String
)

data class imageUrl (
    @SerializedName("url") val url: String
)


