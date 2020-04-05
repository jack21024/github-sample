package com.jack.sample.github.home.data.repository

import android.annotation.SuppressLint
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.jack.sample.github.net.GithubPageLinks
import com.jack.sample.github.home.api.UserListApi
import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.recyclerview.card.enums.CardType
import com.jack.sample.github.recyclerview.card.item.CardItem
import kotlinx.coroutines.*
import retrofit2.Response


class UsersDataSourceFactory : DataSource.Factory<String, CardItem>() {
    override fun create(): DataSource<String, CardItem> {
        return UsersDataSource()
    }
}

class UsersDataSource : PageKeyedDataSource<String, CardItem>(), CoroutineScope by MainScope() {

    private data class UserPage(val users: List<CardItem>, val nextUrl: String)

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, CardItem>
    ) {
        launch {
            UserListApi()
                .setRange(
                    INITIAL_PAGE,
                    PER_PAGE
                )
                .startWithResponse().run {
                    this.result?.let {
                        val userPage = handleResponse(it)!!
                        callback.onResult(userPage.users, null, userPage.nextUrl)
                    }
                }
        }
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, CardItem>) {
        launch {
            val url = params.key
            UserListApi()
                .setNextUrl(url)
                .startWithResponse().run {
                    this.result?.let {
                        val userPage = handleResponse(it)!!
                        callback.onResult(userPage.users, userPage.nextUrl)
                    }
                }
        }

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, CardItem>) {}

    private fun handleResponse(response: Response<List<User>>): UserPage? {
        if (response.isSuccessful) {
            response.body()?.run {
                return UserPage(
                    this.map {
                        CardItem(it.id.hashCode(), CardType.AVATAR, it)
                    },
                    GithubPageLinks.getNext(response.headers())
                )
            }
        }
        return null
    }

    companion object {
        private const val INITIAL_PAGE = 0
        private const val PER_PAGE = 20
    }
}