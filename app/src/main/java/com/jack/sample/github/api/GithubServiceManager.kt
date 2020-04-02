package com.jack.sample.github.api

import com.jack.sample.github.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor



object GithubServiceManager {

    private const val BASE_URL = "https://${GithubService.HOST_API}"

    private val httpClient = OkHttpClient.Builder().run {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            this.addInterceptor(loggingInterceptor)
        }
        this.build()
    }

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    private val githubService =
        retrofit.create(GithubService::class.java)


    val SERVICE: GithubService = githubService

}