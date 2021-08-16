package com.example.museumapp

import com.example.museumapp.models.collectionModels.Welcome
import com.example.museumapp.models.detailModels.DetailWelcome
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

    @GET("/api/en/collection/{object-number}")
    fun getDetailedData(@Path("object-number") objNum: String? = null,
                        @Query("key") key: String? = null): Call<DetailWelcome>
}