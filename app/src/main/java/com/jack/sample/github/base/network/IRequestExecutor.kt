package com.jack.sample.github.base.network

import retrofit2.Response

interface IRequestExecutor {

    suspend fun <Result> request(api: IApi<Result>): Result
    suspend fun <Result> requestWithResponse(api: IApi<Result>): Response<Result>

}