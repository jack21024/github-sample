package com.jack.sample.github.repository

import com.jack.sample.github.api.GitHubServiceManager
import com.jack.sample.github.model.UserRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepoRepository {

    fun getRepository(username: String): Observable<List<UserRepo>> {
        return GitHubServiceManager.service
            .getUserRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }

}