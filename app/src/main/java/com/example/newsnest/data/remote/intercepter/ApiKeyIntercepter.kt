package com.example.newsnest.data.remote.intercepter
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
      //  Log.d("Key", "intercept: ${BuildConfig.NEWS_API_KEY}")
        val originalRequest = chain.request()
        val newUrl = originalRequest.url
            .newBuilder()
            .addQueryParameter(
                "apiKey",
               // "2814a6feb04b4d909c4a98a5439e2362"
                "49f43b8baf5b4208a55e28d86d6fd046"
//                BuildConfig.NEWS_API_KEY
            )
            .build()

        val newRequest = originalRequest
            .newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}