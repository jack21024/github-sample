package com.jack.sample.github.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jack.sample.github.home.data.repository.UserRepoRemoteDateSource
import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.base.repository.BaseRepoData
import com.jack.sample.github.home.data.repository.UserRepoRepository
import com.jack.sample.github.home.data.repository.UserRepoViewData
import com.jack.sample.github.home.data.repository.UserRepository
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    private val usersRepo by lazy { UserRepository() }

    val loading = MutableLiveData<Boolean>()

    val userList = createUserListObservable()

    val userRepoList = MutableLiveData<BaseRepoData<UserRepoViewData>>()


    init {
        loading.value = false
    }

    private fun createUserListObservable(): LiveData<PagedList<User>>? {
        return usersRepo.getUsers()
    }

    fun getUserRepoList(userName: String) {
        MainScope().launch {
            UserRepoRepository(UserRepoRemoteDateSource()).getUserRepoList(userName).let {
                userRepoList.value = (it)
            }
        }
    }

}