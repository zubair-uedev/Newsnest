package com.example.newsnest.domain.model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
