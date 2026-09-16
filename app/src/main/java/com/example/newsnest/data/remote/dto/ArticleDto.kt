package com.example.newsnest.data.remote.dto
import com.example.newsnest.domain.model.Article

data class ArticleDto(
    val source: SourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

fun ArticleDto.toDomain(): Article {
    return Article(
        source = this.source.toDomain(),
        author = this.author ?: "Unknown",
        title = this.title,
        description = this.description ?: "",
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content ?: ""
    )
}

fun Article.toData(): ArticleDto {
    return ArticleDto(
        source = this.source.toData(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}