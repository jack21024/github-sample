package com.jack.sample.github.home.data.repository

import com.jack.sample.github.base.repository.BaseRepoData

class UserRepoRepository(private val remoteDataSource: UserRepoRemoteDateSource) {

    suspend fun getUserRepoList(userName: String): BaseRepoData<UserRepoViewData> {
        return BaseRepoData<UserRepoViewData>().apply {
            remoteDataSource.getUserRepoList(userName).run {
                this.result?.let { data ->
                    viewData.postValue(UserRepoViewData(data))
                }
            }
        }
    }

}