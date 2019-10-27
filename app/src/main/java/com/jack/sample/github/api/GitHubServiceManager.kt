package com.jack.sample.github.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GitHubServiceManager {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val githubService =
        retrofit.create(GitHubService::class.java)

    val service: GitHubService = githubService

    private fun getBaseUrl() = "https://${GitHubService.HOST_API}"

}