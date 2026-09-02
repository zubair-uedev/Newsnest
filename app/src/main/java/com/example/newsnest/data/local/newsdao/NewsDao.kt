package com.example.newsnest.data.local.newsdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsnest.data.local.entity.SaveArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(saveArticleEntity: SaveArticleEntity)

    @Query("DELETE FROM save_news WHERE url = :url")
    suspend fun deleteArticleByUrl(url: String)

    @Query("SELECT * FROM save_news")
    fun getAllSavedArticles(): Flow<List<SaveArticleEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM save_news WHERE url = :url)")
    suspend fun isArticleSaved(url: String): Boolean

}