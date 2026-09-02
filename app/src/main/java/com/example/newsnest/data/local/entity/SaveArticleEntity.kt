package com.example.newsnest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "save_news")
data class SaveArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val url: String?,//for duplicate check
    val uriToImg: String?,
    val title: String,
    val description: String,
    val content: String,
    val publishedAt: String,
)
