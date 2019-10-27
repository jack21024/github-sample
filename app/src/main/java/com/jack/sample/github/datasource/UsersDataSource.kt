package com.jack.sample.github.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.jack.sample.github.api.GitHubPageLinks
import com.jack.sample.github.api.GitHubService
import com.jack.sample.github.api.GitHubServiceManager
import com.jack.sample.github.model.User
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


class UsersDataSourceFactory : DataSource.Factory<String, User>() {
    override fun create(): DataSource<String, User> {
        return UsersDataSource()
    }
}

class UsersDataSource : PageKeyedDataSource<String, User>() {

    private data class UserPage(val users: List<User>, val nextUrl: String)

    companion object {
        private const val INITIAL_PAGE = 0
        private const val PER_PAGE = 20
    }

    private val service: GitHubService = GitHubServiceManager.service

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, User>
    ) {
        service.getUsers(INITIAL_PAGE, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                val userPage = handleResponse(response)!!
                callback.onResult(userPage.users, null, userPage.nextUrl)
            }, {
                Log.d("${UsersDataSource::class}", "loadInitial#error")
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, User>) {
        val url = params.key
        service.getUsers(url)
            .subscribeOn(Schedulers.io())
            .subscribe({response ->
                val userPage = handleResponse(response)!!
                callback.onResult(userPage.users, userPage.nextUrl)
            }, {
                Log.d("${UsersDataSource::class}", "loadAfter#error")
            })

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {}

    private fun handleResponse(response: Response<List<User>>): UserPage? {
        if (response.isSuccessful) {
            response.body()?.run {
                return UserPage(this, GitHubPageLinks.getNext(response.headers()))
            }
        }
        return null
    }

}