package com.jack.sample.github.api

import com.jack.sample.github.base.network.BaseApi
import com.jack.sample.github.model.User
import retrofit2.Response

class UserListApi: BaseApi<GithubService, List<User>>(GithubServiceManager.SERVICE) {

    private var since: Int = 0
    private var perPage: Int = 0
    private var url: String = ""

    fun setRange(since: Int, perPage: Int) = this.apply {
        this.since = since
        this.perPage = perPage
    }

    fun setNextUrl(url: String) = this.apply {
        this.url = url
    }

    override suspend fun getResponse(): Response<List<User>> {
        return if(url.isEmpty()) {
            service.getUsers(since, perPage)
        } else {
            service.getUsers(url)
        }
    }


}