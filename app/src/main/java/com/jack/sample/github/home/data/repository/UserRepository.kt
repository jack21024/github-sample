package com.jack.sample.github.home.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.recyclerview.card.item.CardItem


class UserRepository {

    fun getUsers(): LiveData<PagedList<CardItem>> {
        val source = UsersDataSourceFactory()
        val pagedListConf =
            PagedList.Config.Builder()
                .setPageSize(10)
                .setPrefetchDistance(4)
                .build()
        return LivePagedListBuilder<String, CardItem>(source, pagedListConf).build()
    }

}