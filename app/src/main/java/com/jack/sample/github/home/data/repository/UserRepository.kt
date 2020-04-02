package com.jack.sample.github.home.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jack.sample.github.home.data.entity.User


class UserRepository {

    fun getUsers(): LiveData<PagedList<User>> {
        val source = UsersDataSourceFactory()
        val pagedListConf =
            PagedList.Config.Builder()
                .setPageSize(10)
                .setPrefetchDistance(4)
                .build()
        return LivePagedListBuilder<String, User>(source, pagedListConf).build()
    }

}