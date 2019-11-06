package com.jack.sample.github.ui.githubpage

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jack.sample.github.model.User
import com.jack.sample.github.model.UserRepo
import com.jack.sample.github.repository.UserRepoRepository
import com.jack.sample.github.repository.UsersRepository
import io.reactivex.Observable
import timber.log.Timber

class GithubPageModel : ViewModel() {

    private val usersRepo by lazy { UsersRepository() }

    val loading: ObservableBoolean = ObservableBoolean(false)

    val userList: Observable<PagedList<User>> = createUserListObservable()

    val userRepoList: MutableLiveData<List<UserRepo>> = MutableLiveData()


    private fun createUserListObservable() : Observable<PagedList<User>> {
        return usersRepo.getUsers().concatMap {
            it.addWeakCallback(it, object : PagedList.Callback() {
                override fun onChanged(position: Int, count: Int) { }

                override fun onInserted(position: Int, count: Int) {
                    it.removeWeakCallback(this)
                    if(count > 0) {
                        Timber.d("users is fetched at first, load repositories.")
                        queryUserRepoList(it[0]!!.login)
                    } else {
                        Timber.e("fetch users failed.")
                    }
                }

                override fun onRemoved(position: Int, count: Int) { }

            })

            Observable.just(it)
        }
    }

    fun queryUserRepoList(name: String) {
        UserRepoRepository()
            .getRepository(name)
            .subscribe({
                userRepoList.postValue(it)
            }, {

            })
    }

}