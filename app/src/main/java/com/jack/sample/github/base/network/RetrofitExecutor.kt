package com.jack.sample.github.base.network

import android.accounts.NetworkErrorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RetrofitExecutor : IRequestExecutor {

    override suspend fun <Result> request(api: IApi<Result>): Result =
        withContext(Dispatchers.IO) {
            val data = api.getResponse()
            if (data.isSuccessful) {
                data.body() ?: throw NetworkErrorException("Response no data")
            } else {
                throw NetworkErrorException("Unknown server error")
            }
        }

    override suspend fun <Result> requestWithResponse(api: IApi<Result>): Response<Result> =
        withContext(Dispatchers.IO) {
            api.getResponse()
        }
}
