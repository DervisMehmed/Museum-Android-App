package com.example.museumapp

import com.example.museumapp.models.Welcome
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetDataService {
    @GET("/api/en/collection")
    fun getCollectionData(@Query("key") key: String? = null): Call<Welcome>
}

/*
@GET("/api/{culture}/collection{key}")
    fun getCollectionData(@Path("culture") culture: String? = "en", @Query("key") key: String? = null): Call<Welcome>
 */