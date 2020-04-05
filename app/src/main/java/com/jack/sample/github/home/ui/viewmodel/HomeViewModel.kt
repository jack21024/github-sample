package com.jack.sample.github.home.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.jack.sample.github.home.data.repository.UserRepoRemoteDateSource
import com.jack.sample.github.base.repository.BaseRepoData
import com.jack.sample.github.home.data.HomeViewData
import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.home.data.entity.UserRepo
import com.jack.sample.github.home.data.repository.UserRepoRepository
import com.jack.sample.github.home.data.repository.UserRepoViewData
import com.jack.sample.github.home.data.repository.UserRepository
import com.jack.sample.github.recyclerview.card.item.CardItem
import com.jack.sample.github.recyclerview.row.item.CardRowItem
import com.jack.sample.github.recyclerview.row.item.DetailRowItem
import com.jack.sample.github.recyclerview.row.item.PagedCardRowItem
import com.jack.sample.github.recyclerview.row.item.RepoRowItem
import kotlinx.coroutines.*
import timber.log.Timber

class HomeViewModel : ViewModel(), CoroutineScope by MainScope() {

    private val userRepository by lazy { UserRepository() }
    private val userRepoRepository by lazy { UserRepoRepository(UserRepoRemoteDateSource()) }

    val loading = MutableLiveData<Boolean>()

    val homeViewData = MutableLiveData<HomeViewData>()

    private val _userRepoList = MutableLiveData<BaseRepoData<UserRepoViewData>>()
    val userRepoList: LiveData<List<UserRepo>> = Transformations.map(_userRepoList) {
        it.viewData.value?.userRepoList
    }
    val userRepoRowData: LiveData<List<CardRowItem>> = Transformations.map(userRepoList) {
        ArrayList<CardRowItem>().apply {
            add(DetailRowItem())
            it?.map { repo ->
                RepoRowItem(repo)
            }?.let {
                addAll(it)
            }
        }
    }


    init {
        loading.value = false
    }

    fun getUserRepoList(userName: String) {
        MainScope().launch {
            userRepoRepository.getUserRepoList(userName).let {
                _userRepoList.postValue(it)
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
//        it.addWeakCallback(it, object : PagedList.Callback() {
//            override fun onInserted(position: Int, count: Int) {
//                it.removeWeakCallback(this)
//                if (count > 0) {
//                    Timber.d("users is fetched at first, load repositories.")
//
//                    createHomeViewData(it)
//                } else {
//                    Timber.e("fetch users failed.")
//                }
//            }
//
//            override fun onChanged(position: Int, count: Int) {}
//            override fun onRemoved(position: Int, count: Int) {}
//
//        })
        createHomeViewData(it)
    }

    private fun createHomeViewData(userList: PagedList<CardItem>) {
        launch(Dispatchers.IO) {
            var user = if(userList.isNotEmpty()) {
                userList.first().user
            } else {
                null
            }
            val cardRowList = ArrayList<CardRowItem>().apply {
                add(PagedCardRowItem(userList))
                user?.let {
                    addAll(getUserRepoCardList(user))
                }
            }
            homeViewData.postValue(HomeViewData().apply {
                data = cardRowList
            })
        }
    }

    private suspend fun getUserRepoCardList(user: User): List<CardRowItem> {
        val userRepoViewData = async {
            user.let {
                userRepoRepository.getUserRepoList(it.login)
            }
        }
        val repoList = userRepoViewData.await()?.viewData?.value?.userRepoList

        return ArrayList<CardRowItem>().apply {
            repoList?.let { list ->
                addAll(list.map { repo ->
                    RepoRowItem(repo)
                })
            }
        }
    }
}

