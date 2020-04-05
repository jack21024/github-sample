package com.jack.sample.github.detail.api

import com.jack.sample.github.base.network.BaseApi
import com.jack.sample.github.home.data.entity.UserDetail
import com.jack.sample.github.net.GithubService
import retrofit2.Response

class UserDetailApi : BaseApi<GithubService, UserDetail>(GithubService.getInstance()) {

    private var userName: String = ""

    fun init(userName: String) = this.apply {
        this.userName = userName
    }

    override suspend fun getResponse(): Response<UserDetail> = service.getUserDetail(userName)

}