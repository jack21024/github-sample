package com.jack.sample.github.net

import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.home.data.entity.UserRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GithubService {

    companion object {
        const val HOST_API = "api.github.com" //$NON-NLS-1$
        const val HEADER_LINK = "Link" //$NON-NLS-1$
        const val HEADER_NEXT = "X-Next" //$NON-NLS-1$
        const val HEADER_LAST = "X-Last" //$NON-NLS-1$
        const val META_REL = "rel" //$NON-NLS-1$
        const val META_LAST = "last" //$NON-NLS-1$
        const val META_NEXT = "next" //$NON-NLS-1$
        const val META_FIRST = "first" //$NON-NLS-1$
        const val META_PREV = "prev" //$NON-NLS-1$
    }

    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Response<List<User>>

    @GET
    suspend fun getUsers (
        @Url url: String
    ): Response<List<User>>

//    @GET("/users/{username}")
//    fun getUserIntro (
//        @Path(value = "username") username: String
//    ): Single<UserIntro>

    @GET("/users/{user}/repos")
    suspend fun getUserRepos(
        @Path("user") user: String
    ): Response<List<UserRepo>>

//    @GET("/search/users")
//    fun searchUsers(
//        @Query("q") q: String
//    ): Observable<List<User>>

}