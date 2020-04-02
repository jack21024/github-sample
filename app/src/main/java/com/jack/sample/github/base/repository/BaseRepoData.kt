package com.jack.sample.github.base.repository

import androidx.lifecycle.MutableLiveData

open class BaseRepoData<Data> {
    val viewData = MutableLiveData<Data>()
}