package com.finappproyect.superheroapp.api

import com.finappproyect.superheroapp.model.SuperHeroDataResponse
import com.finappproyect.superheroapp.model.SuperHeroDetail
import com.finappproyect.superheroapp.model.SuperHeroItemDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/d72249edba435b830aa27ad27a7f8b9b/search/{name}")
    suspend fun getSuperheros(@Path("name") nameSuperHero : String): Response<SuperHeroDataResponse>

    @GET("/api/d72249edba435b830aa27ad27a7f8b9b/{id}")
    suspend fun getSuperHeroID(@Path("id") superHeroID : String): Response<SuperHeroDetail>

}
