package com.example.newsnest.data.remote.maper
import com.example.newsnest.data.remote.dto.ArticleDto
import com.example.newsnest.data.remote.dto.NewsResponseDto
import com.example.newsnest.data.remote.dto.SourceDto
import com.example.newsnest.domain.model.Article
import com.example.newsnest.domain.model.NewsResponse
import com.example.newsnest.domain.model.Source




fun SourceDto.toDomain(): Source {
    return Source(
        id = this.id,
        name = this.name
    )
}


fun ArticleDto.toDomain(): Article {
    return Article(
        source = source.toDomain(),
        author = author ?: "Unknown",
        title = title,
        description = description ?: "",
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content ?: ""
    )
}


fun NewsResponseDto.toDomain(): NewsResponse {
    return NewsResponse(
        status = this.status,
        totalResults = this.totalResults,
        articles = this.articles.map { it.toDomain() }
    )
}


//DOMAIN TO DTO

fun Source.toData(): SourceDto {
    return SourceDto(
        id = this.id,
        name = this.name
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


fun NewsResponse.toData(): NewsResponseDto {
    return NewsResponseDto(
        status = this.status,
        totalResults = this.totalResults,
        articles = this.articles.map { it.toData() }
    )
}