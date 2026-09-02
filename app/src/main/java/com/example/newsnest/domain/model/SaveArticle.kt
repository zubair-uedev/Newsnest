package com.example.newsnest.domain.model
data class SaveArticle(
    val url: String?,
    val urlToImage: String?,
    val title: String,
    val description: String,
    val content: String,
    val publishedAt: String
)
