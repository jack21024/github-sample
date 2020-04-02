package com.jack.sample.github.base.network

import retrofit2.Response

interface IApi<Result> {

    suspend fun getResponse(): Response<Result>

}
