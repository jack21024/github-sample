package com.jack.sample.github.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jack.sample.github.base.repository.BaseRepoData
import com.jack.sample.github.detail.repository.UserDetailRemoteDataSource
import com.jack.sample.github.detail.repository.UserDetailRepository
import com.jack.sample.github.home.data.entity.UserDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel(), CoroutineScope by MainScope() {

    private val repository by lazy { UserDetailRepository(UserDetailRemoteDataSource()) }

    val _detailViewData = MutableLiveData<BaseRepoData<UserDetail>>()
    val detailViewData = Transformations.map(_detailViewData) {
        it.viewData
    }


    fun getData(userName: String) {
        launch {
            repository.getUserDetail(userName).let {
                _detailViewData.postValue(it)
            }
        }
    }


}