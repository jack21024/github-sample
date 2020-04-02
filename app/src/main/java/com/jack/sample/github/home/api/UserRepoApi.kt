package com.jack.sample.github.home.api

import com.jack.sample.github.net.GithubService
import com.jack.sample.github.net.GithubServiceManager
import com.jack.sample.github.base.network.BaseApi
import com.jack.sample.github.home.data.entity.UserRepo
import retrofit2.Response

class UserRepoApi : BaseApi<GithubService, List<UserRepo>>(GithubServiceManager.SERVICE) {

    private var userName: String = ""

    fun init(userName: String) = this.apply {
        this.userName = userName
    }

    override suspend fun getResponse(): Response<List<UserRepo>>
            = service.getUserRepos(userName)

}
