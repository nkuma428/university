package com.example.university.data.remote

import com.example.university.util.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// API service interface for Retrofit to handle network requests
interface ApiService {

    // This function makes a GET request to the "search" endpoint with a "country" query parameter
    @GET("search")
    suspend fun getUniversitiesByCountry(@Query("country") country: String): List<University>

    // Companion object for creating an instance of ApiService using Retrofit
    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}