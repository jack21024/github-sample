package com.jack.sample.github.api

import com.jack.sample.github.base.network.BaseApi
import com.jack.sample.github.model.UserRepo
import retrofit2.Response

class UserRepoApi : BaseApi<GithubService, List<UserRepo>>(GithubServiceManager.SERVICE) {

    private var userName: String = ""

    fun init(userName: String) = this.apply {
        this.userName = userName
    }

    override suspend fun getResponse(): Response<List<UserRepo>>
            = service.getUserRepos(userName)

}
