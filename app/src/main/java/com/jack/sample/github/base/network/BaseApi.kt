package com.jack.sample.github.base.network

import com.jack.sample.github.base.network.data.ResponseData
import retrofit2.Response


abstract class BaseApi<Service, Result>(protected val service: Service) : IApi<Result> {

    protected val parameters: HashMap<String, String> = hashMapOf()

    open suspend fun start(
        executor: IRequestExecutor = RetrofitExecutor()
    ): ResponseData<Result> {
        val requestResult = executor.request(this)
        return ResponseData<Result>().apply {
            this.result = requestResult
        }
    }

    open suspend fun startWithResponse(
        executor: IRequestExecutor = RetrofitExecutor()
    ): ResponseData<Response<Result>> {
        return ResponseData<Response<Result>>().apply {
            this.result = executor.requestWithResponse(this@BaseApi)
        }
    }



}