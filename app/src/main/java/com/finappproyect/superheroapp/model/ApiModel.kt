package com.finappproyect.superheroapp.model

import com.google.gson.annotations.SerializedName


data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val results: List<SuperHeroItemDataResponse>
)

data class SuperHeroItemDataResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val superHeroName: String,
    @SerializedName("biography") val superHeroBiography: SuperHeroPublisher,
    @SerializedName("image") val superHeroImage: SuperHeroImageUrl
)

data class SuperHeroPublisher(
    @SerializedName("publisher") val publisher: String
)

data class SuperHeroImageUrl(
    @SerializedName("url") val superHeroImageUrl: String
)