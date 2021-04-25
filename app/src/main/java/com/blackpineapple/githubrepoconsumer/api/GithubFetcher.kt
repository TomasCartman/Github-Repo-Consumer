package com.blackpineapple.githubrepoconsumer.api

import com.blackpineapple.githubrepoconsumer.data.model.Repository
import retrofit2.Call
import javax.inject.Inject

class GithubFetcher @Inject constructor(var githubApi: GithubApi) {
    //@Inject
     //lateinit var githubApi: GithubApi

    /*
    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        githubApi = retrofit.create(GithubApi::class.java)
    }
     */

    fun searchRepositories(query: String): Call<List<Repository>> {
        return githubApi.searchRepositories(query)
    }

    fun getAllPublicRepositories(): Call<List<Repository>> {
        return githubApi.getAllPublicRepositories()
    }
}