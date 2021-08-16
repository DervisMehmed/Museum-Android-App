package com.example.museumapp.models.collectionModels

import com.example.museumapp.GetDataService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val SERVICE_URL = "https://www.rijksmuseum.nl/"

    val retrofitInstance: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .baseUrl(SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
    }

    val retroInterface: GetDataService by lazy {
        retrofitInstance
                .build()
                .create(GetDataService::class.java)
    }
}