package com.example.newsnest.presentation.homescreen.savescreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsnest.domain.model.SaveArticle
import com.example.newsnest.domain.repo.SaveNewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SaveViewModel @Inject constructor(private val saveNewsRepo: SaveNewsRepo) : ViewModel() {
    private val _saveNews = MutableStateFlow<List<SaveArticle>>(emptyList())
    val saveNews = _saveNews.asStateFlow()
    init {
        getAllArticle()
    }
    fun deleteArticleByUrl(url: String) {
        viewModelScope.launch {
            saveNewsRepo.deleteArticleByUrl(url)
        }
    }
    fun getAllArticle() {
        viewModelScope.launch {
            saveNewsRepo.getNews()
                .collectLatest { it ->
                    _saveNews.value = it
                }
        }
    }
}