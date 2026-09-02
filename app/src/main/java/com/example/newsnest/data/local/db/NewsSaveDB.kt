package com.example.newsnest.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsnest.data.local.entity.SaveArticleEntity
import com.example.newsnest.data.local.newsdao.NewsDao

@Database(entities = [SaveArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsSaveDB : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}