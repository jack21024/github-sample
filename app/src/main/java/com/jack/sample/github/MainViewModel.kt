package com.jack.sample.github

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jack.sample.github.model.User
import com.jack.sample.github.repository.UsersRepository
import io.reactivex.Observable

class MainViewModel: ViewModel() {

    private val usersRepo by lazy { UsersRepository() }

    val loading: ObservableBoolean = ObservableBoolean(false)

    val userList: Observable<PagedList<User>> = usersRepo.getUsers()

}