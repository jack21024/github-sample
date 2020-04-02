package com.jack.sample.github.home.data.repository

import com.jack.sample.github.home.api.UserRepoApi
import com.jack.sample.github.base.network.data.ResponseData
import com.jack.sample.github.home.data.entity.UserRepo

class UserRepoRemoteDateSource : UserRepoDataSource {

    override suspend fun getUserRepoList(userName: String): ResponseData<List<UserRepo>> =
        UserRepoApi().init(userName).start()

}

interface UserRepoDataSource {

    suspend fun getUserRepoList(userName: String): ResponseData<List<UserRepo>>

}
