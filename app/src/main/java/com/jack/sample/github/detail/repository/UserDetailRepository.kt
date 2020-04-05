package com.jack.sample.github.detail.repository

import com.jack.sample.github.base.repository.BaseRepoData
import com.jack.sample.github.home.data.entity.UserDetail

class UserDetailRepository(private val remoteDataSource: UserDetailRemoteDataSource) {

    suspend fun getUserDetail(userName: String): BaseRepoData<UserDetail> {
        return BaseRepoData<UserDetail>().apply {
            remoteDataSource.getUserDetail(userName).let {
                it.result?.let { data ->
                    viewData.postValue(data)
                }
            }
        }
    }

}