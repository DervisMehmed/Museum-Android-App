package com.example.museumapp

import com.example.museumapp.models.Welcome
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("/{culture}/collection{key}")
    fun getCollectionData(@Query("key") key: String? = null): Call<Welcome>
}