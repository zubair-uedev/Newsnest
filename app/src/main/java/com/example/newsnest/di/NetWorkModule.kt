package com.example.newsnest.di

import android.content.Context
import androidx.room.Room
import com.example.newsnest.data.local.db.NewsSaveDB
import com.example.newsnest.data.local.newsdao.NewsDao
import com.example.newsnest.data.remote.api.NewApiService
import com.example.newsnest.data.remote.api.UserApi
import com.example.newsnest.data.remote.intercepter.ApiKeyInterceptor
import com.example.newsnest.utils.Constant.BASE_URL
import com.example.newsnest.utils.Constant.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserNetworkModule {
    @UserRetrofit
    @Provides
    @Singleton
    fun provideUserRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BaseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(
        @UserRetrofit retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}

//news work start hear
@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor()
    }

    @Provides
    @Singleton
    fun provideNewsOkHttpClient(
        interceptor: ApiKeyInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    @NewsRetrofit
    fun provideNewsRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(
        @NewsRetrofit retrofit: Retrofit
    ): NewApiService {
        return retrofit.create(NewApiService::class.java)
    }
}


//save news provide
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun databaseInstanceCreator(
        @ApplicationContext mContext: Context
    ): NewsSaveDB {
        return Room.databaseBuilder(
            context = mContext,
            klass = NewsSaveDB::class.java,
            name = "save_news_db"
        ).build()
    }

    @Singleton
    @Provides
    fun insertDataFromDataBaseToDao(saveDB: NewsSaveDB): NewsDao {
        return saveDB.newsDao()
    }
}