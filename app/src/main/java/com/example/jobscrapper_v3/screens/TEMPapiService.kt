package com.example.jobscrapper_v3.screens

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("internshala/android")
    suspend fun getAndroidData(): List<post>

    @GET("index")
    suspend fun getCategories(): List<sideBarItem>

    @GET("internshala/{category}")
    suspend fun getInternshalaData(@Path("category") category: String): List<post>
}

object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)  // Increase connection timeout
        .readTimeout(60, TimeUnit.SECONDS)     // Increase read timeout
        .writeTimeout(60, TimeUnit.SECONDS)    // Increase write timeout
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.106.212.53/")
            .client(client) // Set custom OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
