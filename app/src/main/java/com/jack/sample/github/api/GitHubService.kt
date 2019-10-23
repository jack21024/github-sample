package com.jack.sample.github.api

import com.jack.sample.github.model.User
import com.jack.sample.github.model.UserIntro
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubService {

    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    @GET("/users")
    fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Single<List<User>>

    @GET
    fun getUsers (
        @Url url: String
    ): io.reactivex.Single<Response<List<User>>>

    @GET("/users/{username}")
    fun getUserIntro (
        @Path(value = "username") username: String
    ): Single<UserIntro>

    @GET("/users/{user}/repos")
    fun getUserRepos(
        @Query("user") user: String
    ): Call<Any>

    @GET("/search/users")
    fun searchUsers(
        @Query("q") q: String
    ): Observable<List<User>>

}