package com.jack.sample.github.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GitHubServiceImpl {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(GitHubService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val githubService =
        retrofit.create(GitHubService::class.java)

    val service: GitHubService = githubService
}