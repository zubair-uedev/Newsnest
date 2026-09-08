package com.example.newsnest.presentation.detailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsnest.domain.model.Article
import com.example.newsnest.domain.model.SaveArticle
import com.example.newsnest.domain.repo.SaveNewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveNewsRepo: SaveNewsRepo
) : ViewModel() {

    val article: Article? = savedStateHandle.get<Article>("article")

    fun saveNews(saveArticle: SaveArticle) {
        viewModelScope.launch {
            saveNewsRepo.saveNews(saveArticle = saveArticle)
        }
    }

    fun deleteArticleByUrl(url: String) {
        viewModelScope.launch {
            saveNewsRepo.deleteArticleByUrl(url)
        }
    }


    fun isArticleSaved(url: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isSaved = saveNewsRepo.isArticleSave(url)
            onResult(isSaved)
        }
    }


}