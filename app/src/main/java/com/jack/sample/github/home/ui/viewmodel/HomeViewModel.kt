package com.jack.sample.github.home.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.jack.sample.github.home.api.UserListApi
import com.jack.sample.github.home.data.repository.UserRepoRemoteDateSource
import com.jack.sample.github.home.data.HomeViewData
import com.jack.sample.github.home.data.repository.UserRepoRepository
import com.jack.sample.github.home.data.repository.UserRepository
import com.jack.sample.github.recyclerview.card.item.CardItem
import com.jack.sample.github.recyclerview.row.item.CardRowItem
import com.jack.sample.github.recyclerview.row.item.DetailRowItem
import com.jack.sample.github.recyclerview.row.item.PagedCardRowItem
import com.jack.sample.github.recyclerview.row.item.RepoRowItem
import kotlinx.coroutines.*

class HomeViewModel : ViewModel(), CoroutineScope by MainScope() {

    private val userRepository by lazy { UserRepository() }
    private val userRepoRepository by lazy { UserRepoRepository(UserRepoRemoteDateSource()) }

    val loading = MutableLiveData<Boolean>()

    val homeViewData = MutableLiveData<HomeViewData>()

    val userRepoRowData = MutableLiveData<List<CardRowItem>>()


    init {
        loading.value = false
    }

    fun getUserRepoList(userName: String) {
        MainScope().launch {
            userRepoRepository.getUserRepoList(userName).let {
                delay(1) // workaround to fix livedata get null value event it's post

                it.viewData.value?.userRepoList.let {
                    userRepoRowData.postValue(
                        ArrayList<CardRowItem>().apply {
                            add(DetailRowItem(userName))
                            it?.map { repo ->
                                RepoRowItem(repo)
                            }?.let {
                                addAll(it)
                            }
                        }
                    )
                }
            }
        }
    }

    fun start() {
        createUserPagedListLiveData().observeForever(userLiveDataObserver)
    }

    private fun createUserPagedListLiveData(): LiveData<PagedList<CardItem>> {
        return userRepository.getUsers()
    }

    private val userLiveDataObserver = Observer<PagedList<CardItem>> {
        createHomeViewData(it)
        createUserRepoViewData()
    }

    private fun createHomeViewData(userList: PagedList<CardItem>) {
        homeViewData.postValue(HomeViewData().apply {
            data = ArrayList<CardRowItem>().apply {
                add(PagedCardRowItem(userList))
            }
        })
    }

    private fun createUserRepoViewData() {
        launch(Dispatchers.IO) {
            val userData = async {
                UserListApi()
                    .setRange(0,1)
                    .start()
            }
            val user = userData.await().result?.first()
            user?.let {
               getUserRepoList(it.login)
            }

        }
    }

}

