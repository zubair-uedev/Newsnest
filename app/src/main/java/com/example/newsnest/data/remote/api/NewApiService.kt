package com.example.newsnest.data.remote.api

import com.example.newsnest.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApiService {
    @GET("top-headlines")
    suspend fun getTopHeadline(
        @Query("country") country: String = "us",
        @Query("category") category: String? = null
    ): NewsResponseDto

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String = "publishedAt"
    ): NewsResponseDto
}