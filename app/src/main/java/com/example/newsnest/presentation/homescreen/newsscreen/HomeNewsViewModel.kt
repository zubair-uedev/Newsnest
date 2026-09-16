package com.example.newsnest.presentation.homescreen.newsscreen
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsnest.domain.model.Article
import com.example.newsnest.domain.model.NewsResponse
import com.example.newsnest.domain.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeNewsViewModel @Inject constructor(private val newsRepo: NewsRepo) : ViewModel() {
    private val _newResponseData = MutableStateFlow<NewsResponse?>(null)
    val newResponseData = _newResponseData.asStateFlow()

    private val _newsMessage = MutableSharedFlow<NewsDashBoard>()
    val newsMessage = _newsMessage.asSharedFlow()

    fun onNewsCLickArticle(article: Article) {
        viewModelScope.launch {
            _newsMessage.emit(NewsDashBoard.NavigateToDetailScreen(article))
        }
    }

    fun getHeadLines(country: String = "us", category: String? = null) {
        viewModelScope.launch {
            try {
                val newsResponse = newsRepo.getToHeadlines(country = country, category = category)
                _newResponseData.value = newsResponse
                Log.d("NewsDebug", "Success: ${newsResponse.articles.size} articles fetched")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("NewsDebug", "Error fetching news: ${e.message}", e)
            }
        }
    }
}

sealed class NewsDashBoard {
    data class NavigateToDetailScreen(val article: Article) : NewsDashBoard()
}