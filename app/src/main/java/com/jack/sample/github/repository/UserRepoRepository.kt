package com.jack.sample.github.repository

import com.jack.sample.github.base.repository.BaseRepoData
import com.jack.sample.github.datasource.UserRepoRemoteDateSource

class UserRepoRepository(private val remoteDataSource: UserRepoRemoteDateSource) {

    suspend fun getUserRepoList(userName: String): BaseRepoData<UserRepoViewData> {
        return BaseRepoData<UserRepoViewData>().apply {
            remoteDataSource.getUserRepoList(userName).run {
                this.result?.let { data ->
                    viewData.value = (UserRepoViewData(data))
                }
            }
        }
    }

}