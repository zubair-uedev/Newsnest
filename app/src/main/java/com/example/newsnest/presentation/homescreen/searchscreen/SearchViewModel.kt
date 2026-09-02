package com.example.newsnest.presentation.homescreen.searchscreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsnest.domain.model.NewsResponse
import com.example.newsnest.domain.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val newsRepo: NewsRepo) : ViewModel() {
    private val _searchState = MutableStateFlow<NewsResponse?>(null)
    val searchState = _searchState.asStateFlow()
    fun searchNews(query: String) {
        viewModelScope.launch {
            try {
                val searchResponse = newsRepo.searchNews(query = query)
                _searchState.value = searchResponse
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //by defauly news
    fun loadDefaultNews() {
        viewModelScope.launch {
            try {
                val response = newsRepo.searchNews(query = "news")
                _searchState.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}