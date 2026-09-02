package com.example.newsnest.data.remote.repository
import com.example.newsnest.data.remote.api.NewApiService
import com.example.newsnest.data.remote.maper.toDomain
import com.example.newsnest.domain.model.NewsResponse
import com.example.newsnest.domain.repo.NewsRepo
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newApiService: NewApiService) : NewsRepo {
    override suspend fun getToHeadlines(
        country: String,
        category: String?
    ): NewsResponse {
        return newApiService.getTopHeadline(
            country = country,
            category = category
        ).toDomain()
    }

    override suspend fun searchNews(query: String): NewsResponse {
        return newApiService.searchNews(
            query = query
        ).toDomain()
    }
}