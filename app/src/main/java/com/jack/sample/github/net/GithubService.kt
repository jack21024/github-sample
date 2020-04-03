package com.jack.sample.github.net

import com.jack.sample.github.BuildConfig
import com.jack.sample.github.home.data.entity.User
import com.jack.sample.github.home.data.entity.UserRepo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GithubService {

    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Response<List<User>>

    @GET
    suspend fun getUsers(
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

    companion object {

        private const val BASE_URL = "https://api.github.com"

        @Volatile
        private var INSTANCE: GithubService? = null

        fun getInstance(): GithubService =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: create().also {
                    INSTANCE = it
                }
            }

        private fun create(): GithubService {
            val client = OkHttpClient.Builder().run {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    this.addInterceptor(loggingInterceptor)
                }
                this.build()
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(GithubService::class.java)
        }
    }

}