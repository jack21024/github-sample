package com.jack.sample.github

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jack.sample.github.model.User
import com.jack.sample.github.model.UserRepo
import com.jack.sample.github.repository.UserRepoRepository
import com.jack.sample.github.repository.UsersRepository
import io.reactivex.Observable

class MainViewModel : ViewModel() {

    private val usersRepo by lazy { UsersRepository() }

    val loading: ObservableBoolean = ObservableBoolean(false)

    val userList: Observable<PagedList<User>> = createUserListObservable()

    val userRepoList: MutableLiveData<List<UserRepo>> = MutableLiveData()


    private fun createUserListObservable() : Observable<PagedList<User>> {
        return usersRepo.getUsers().concatMap {
            it.addWeakCallback(it, object : PagedList.Callback() {
                override fun onChanged(position: Int, count: Int) {}

                override fun onInserted(position: Int, count: Int) {
                    it.removeWeakCallback(this)
                    if(count > 0) {
                        queryUserRepoList(it[0]!!.login)
                    }
                }

                override fun onRemoved(position: Int, count: Int) {}

            })

            Observable.just(it)
        }
    }

    fun queryUserRepoList(name: String) {
        UserRepoRepository()
            .getRepository(name)
            .subscribe({
                Log.d("MainViewModel", "queryUserRepoList#")
                userRepoList.postValue(it)
            }, {

            })
    }

}