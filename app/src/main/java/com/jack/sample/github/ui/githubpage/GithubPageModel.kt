package com.jack.sample.github.ui.githubpage

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jack.sample.github.datasource.UserRepoRemoteDateSource
import com.jack.sample.github.model.User
import com.jack.sample.github.base.repository.BaseRepoData
import com.jack.sample.github.repository.UserRepoRepository
import com.jack.sample.github.repository.UserRepoViewData
import com.jack.sample.github.repository.UsersRepository
import kotlinx.coroutines.*
import timber.log.Timber

class GithubPageModel : ViewModel() {

    private val usersRepo by lazy { UsersRepository() }

    val loading: ObservableBoolean = ObservableBoolean(false)

    val userList = createUserListObservable()

    val userRepoList = MutableLiveData<BaseRepoData<UserRepoViewData>>()



    private fun createUserListObservable(): LiveData<PagedList<User>>? {
        return usersRepo.getUsers()

    }
//    private fun createUserListObservable(): Observable<PagedList<User>> {
//        return usersRepo.getUsers().concatMap {
//            it.addWeakCallback(it, object : PagedList.Callback() {
//                override fun onChanged(position: Int, count: Int) {}
//
//                override fun onInserted(position: Int, count: Int) {
//                    it.removeWeakCallback(this)
//                    if (count > 0) {
//                        Timber.d("users is fetched at first, load repositories.")
//                        getUserRepoList(it[0]!!.login)
//                    } else {
//                        Timber.e("fetch users failed.")
//                    }
//                }
//
//                override fun onRemoved(position: Int, count: Int) {}
//
//            })
//
//            Observable.just(it)
//        }
//    }


    fun getUserRepoList(userName: String) {
        MainScope().launch {
            UserRepoRepository(UserRepoRemoteDateSource()).getUserRepoList(userName).let {
                userRepoList.value = (it)
            }
        }
    }

}