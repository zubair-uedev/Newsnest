package com.example.newsnest.data.remote.dto

import com.example.newsnest.domain.model.NewsResponse

data class NewsResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)

fun NewsResponseDto.toDomain(): NewsResponse {
    return NewsResponse(
        status = this.status,
        totalResults = this.totalResults,
        articles = this.articles.map { it.toDomain() }
    )
}

fun NewsResponse.toData(): NewsResponseDto {
    return NewsResponseDto(
        status = this.status,
        totalResults = this.totalResults,
        articles = this.articles.map { it.toData() }
    )
}