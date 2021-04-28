package com.blackpineapple.githubrepoconsumer.api

import com.blackpineapple.githubrepoconsumer.data.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/repositories")
    fun searchRepositories(@Query("q") query: String): Call<SearchItems>

    @GET("/repositories")
    fun getAllPublicRepositories(): Call<List<Repository>>
}