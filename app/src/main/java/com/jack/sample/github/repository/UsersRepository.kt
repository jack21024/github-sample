package com.jack.sample.github.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jack.sample.github.datasource.UsersDataSourceFactory
import com.jack.sample.github.model.User


class UsersRepository {

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