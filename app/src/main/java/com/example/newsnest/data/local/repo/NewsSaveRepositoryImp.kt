package com.example.newsnest.data.local.repo
import com.example.newsnest.data.local.maper.toData
import com.example.newsnest.data.local.maper.toDomain
import com.example.newsnest.data.local.newsdao.NewsDao
import com.example.newsnest.domain.model.SaveArticle
import com.example.newsnest.domain.repo.SaveNewsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsSaveRepositoryImp @Inject constructor(private val newsDao: NewsDao) : SaveNewsRepo {
    override suspend fun saveNews(saveArticle: SaveArticle) {
        newsDao.saveArticle(saveArticle.toData())
    }

    override suspend fun deleteArticleByUrl(uri: String) {
        newsDao.deleteArticleByUrl(url = uri)
    }

    override suspend fun isArticleSave(uri: String): Boolean {
        return newsDao.isArticleSaved(url = uri)
    }

    override fun getNews(): Flow<List<SaveArticle>> {
        return newsDao.getAllSavedArticles().map { saveArticle ->
            saveArticle.map { it ->
                it.toDomain()
            }
        }
    }
}