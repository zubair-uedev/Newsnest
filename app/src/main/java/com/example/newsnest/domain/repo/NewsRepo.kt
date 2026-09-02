package com.example.newsnest.domain.repo

import com.example.newsnest.domain.model.NewsResponse

interface NewsRepo {
    suspend fun getToHeadlines(country: String, category: String?): NewsResponse
    suspend fun searchNews(query: String): NewsResponse

}