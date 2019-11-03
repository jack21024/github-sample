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
            Log.e("Card", "MainViewModel#createUserListObservable#usersRepo")
            it.addWeakCallback(it, object : PagedList.Callback() {
                override fun onChanged(position: Int, count: Int) {
                    Log.e("MainViewModel", "MainViewModel#onChanged")
                }

                override fun onInserted(position: Int, count: Int) {
                    Log.e("Card", "MainViewModel#onInserted callback=${this}")
                    it.removeWeakCallback(this)
                    if(count > 0) {
                        Log.e("Card", "MainViewModel#queryUserRepoList")
                        queryUserRepoList(it[0]!!.login)
                    }
                }

                override fun onRemoved(position: Int, count: Int) {
                    Log.e("Card", "MainViewModel#onRemoved")
                }

            })

            Observable.just(it)
        }
    }

    fun queryUserRepoList(name: String) {
        Log.d("Card", "MainViewModel#queryUserRepoList# login=$name")
        UserRepoRepository()
            .getRepository(name)
            .subscribe({
                Log.d("Card", "MainViewModel#queryUserRepoList#subscribe repoList.size=${it.size}")
                userRepoList.postValue(it)
            }, {

            })
    }

}