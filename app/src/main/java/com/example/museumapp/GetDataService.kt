package com.example.museumapp

import com.example.museumapp.models.Welcome
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("/{culture}/collection{key}")
    fun getCollectionData(): Call<Welcome>
}