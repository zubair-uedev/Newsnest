package com.example.newsnest.di

import com.example.newsnest.data.local.repo.NewsSaveRepositoryImp
import com.example.newsnest.data.remote.repository.NewsRepository
import com.example.newsnest.domain.repo.NewsRepo
import com.example.newsnest.domain.repo.SaveNewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
abstract class NewsRepositoryModule {
    @Binds
    abstract fun bindNewsRepo(newsRepository: NewsRepository): NewsRepo
}

//save news provide

//save news bind
//bind
@Module
@InstallIn(SingletonComponent::class)
abstract class SaveNews {
    @Binds
    abstract fun bindNewsRepository(newsSaveRepositoryImp: NewsSaveRepositoryImp): SaveNewsRepo
}