package com.jack.sample.github.datasource

import android.annotation.SuppressLint
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.jack.sample.github.api.GithubPageLinks
import com.jack.sample.github.api.GithubService
import com.jack.sample.github.api.GithubServiceManager
import com.jack.sample.github.model.User
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber


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

    private val service: GithubService = GithubServiceManager.SERVICE

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
                Timber.e(it)
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
                Timber.e(it)
            })

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, User>) {}

    private fun handleResponse(response: Response<List<User>>): UserPage? {
        if (response.isSuccessful) {
            response.body()?.run {
                return UserPage(this, GithubPageLinks.getNext(response.headers()))
            }
        }
        return null
    }

}