package com.example.jobscrapper_v3.screens

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

// Retrofit API Interface
interface ApiService {
    @GET("index")
    suspend fun getCategories(): List<sideBarItem>

    @GET("internshala/{category}")
    suspend fun getInternshalaData(@Path("category") category: String): List<post>
    @GET("internshala/android")
    suspend fun getAndroidData(): List<post>
}

// OkHttpClient with increased timeouts
val client = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS) // Increase connection timeout
    .readTimeout(60, TimeUnit.SECONDS)    // Increase read timeout
    .writeTimeout(60, TimeUnit.SECONDS)   // Increase write timeout
    .build()

// Retrofit Instance with OkHttpClient
object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.106.212.53/") // Ensure this is correct
            .addConverterFactory(GsonConverterFactory.create())
            .client(client) // Attach OkHttpClient with timeouts
            .build()
            .create(ApiService::class.java)
    }
}
