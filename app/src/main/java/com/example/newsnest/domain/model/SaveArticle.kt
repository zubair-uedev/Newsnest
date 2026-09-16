package com.example.newsnest.domain.model

import com.example.newsnest.data.local.entity.SaveArticleEntity

data class SaveArticle(
    val url: String?,
    val urlToImage: String?, // image .jpg in room
    val title: String,
    val description: String,
    val content: String,
    val publishedAt: String
)


