package com.jack.sample.github.detail.repository

import com.jack.sample.github.base.network.data.ResponseData
import com.jack.sample.github.detail.api.UserDetailApi
import com.jack.sample.github.home.data.entity.UserDetail

class UserDetailRemoteDataSource: UserDetailDataSource {

    override suspend fun getUserDetail(userName: String): ResponseData<UserDetail> {
        return UserDetailApi()
            .init(userName)
            .start()
    }

}

interface UserDetailDataSource {

    suspend fun getUserDetail(userName: String): ResponseData<UserDetail>

}