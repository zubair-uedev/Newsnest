package com.example.newsnest.data.local.maper

import com.example.newsnest.data.local.entity.SaveArticleEntity
import com.example.newsnest.domain.model.SaveArticle

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
