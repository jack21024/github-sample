package com.jack.sample.github.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.jack.sample.github.datasource.UsersDataSourceFactory
import com.jack.sample.github.model.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UsersRepository {


    fun getUsers(): Observable<PagedList<User>> {
        val source = UsersDataSourceFactory()
        val pagedListConf =
            PagedList.Config.Builder()
                .setPageSize(10)
                .setPrefetchDistance(4)
                .build()

        return RxPagedListBuilder(source, pagedListConf)
            .setFetchScheduler(Schedulers.io())
            .setNotifyScheduler(AndroidSchedulers.mainThread())
            .buildObservable()
    }

}