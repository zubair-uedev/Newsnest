package com.example.newsnest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsnest.domain.model.SaveArticle

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

fun SaveArticleEntity.toDomain(): SaveArticle {
    return SaveArticle(
        url = this.url,
        urlToImage = this.uriToImg,
        title = this.title,
        description = this.description,
        content = this.content,
        publishedAt = this.publishedAt
    )
}

fun SaveArticle.toData(): SaveArticleEntity {
    return SaveArticleEntity(
        url = this.url,
        uriToImg = this.urlToImage,
        title = this.title,
        description = this.description,
        content = this.content,
        publishedAt = this.publishedAt
    )
}
