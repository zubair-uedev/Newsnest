package com.example.newsnest.domain.repo

import com.example.newsnest.domain.model.SaveArticle
import kotlinx.coroutines.flow.Flow

interface SaveNewsRepo {
    suspend fun saveNews(saveArticle: SaveArticle)
    suspend fun deleteArticleByUrl(uri: String)
    suspend fun isArticleSave(uri: String): Boolean
    fun getNews(): Flow<List<SaveArticle>>
}