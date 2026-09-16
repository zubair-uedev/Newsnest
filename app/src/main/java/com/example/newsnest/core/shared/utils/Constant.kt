package com.example.newsnest.core.shared.utils

import android.content.Context
import com.example.newsnest.domain.model.NewsCategory

object Constant {
    const val Tag = "mzubair"
    const val BaseUrl = "https://graduate-charity-private.ngrok-free.dev/"

    //    const val BASE_URL = "https://newsapi.org/"
    //  const val BASE_URL = "https://newsapi.org/v2/"
    const val BASE_URL = "https://newsapi.org/v2/"
//    const val API_KEY = "2814a6feb04b4d909c4a98a5439e2362"
}

//pref
object Pref {
    private const val PrefName = "nest"
    private const val key = "onboarding_completed"

    fun setOnBoardPref(context: Context, complete: Boolean) {
        context.getSharedPreferences(PrefName, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(key, complete)
            .apply()
    }

    fun isOnBoardingComplete(context: Context): Boolean {
        return context.getSharedPreferences(PrefName, Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }
}

object NewsCategories {
    val list = listOf(
        NewsCategory("LATEST", null),
        NewsCategory("BUSINESS", "business"),
        NewsCategory("ENTERTAINMENT", "entertainment"),
        NewsCategory("GENERAL", "general"),
        NewsCategory("HEALTH", "health"),
        NewsCategory("SCIENCE", "science"),
        NewsCategory("SPORTS", "sports"),
        NewsCategory("TECHNOLOGY", "technology")
    )
}