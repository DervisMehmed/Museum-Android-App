package com.example.museumapp

import com.example.museumapp.models.Welcome
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetDataService {
    @GET("/api/en/collection")
    fun getCollectionData(
        @Query("key") key: String? = null,
        @Query("p") page: String? = null,
        @Query("involvedMaker") maker: String? = null,
        @Query("type") type: String? = null,
        @Query("material") material: String? = null,
        @Query("technique") technique: String? = null,
        @Query("f.dating.period") period: String? = null
        ): Call<Welcome>
}