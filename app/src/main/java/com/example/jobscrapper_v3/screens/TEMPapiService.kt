package com.example.jobscrapper_v3.screens

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("index")
    suspend fun getCategories() : List<sideBarItem>

    @GET("internshala/{category}")
    suspend fun getInternshalaData(@Path("category") category: String): List<post>
}
object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://my-flask-api-8d18.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}