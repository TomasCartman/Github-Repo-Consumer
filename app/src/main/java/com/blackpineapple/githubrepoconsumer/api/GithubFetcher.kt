package com.blackpineapple.githubrepoconsumer.api

import com.blackpineapple.githubrepoconsumer.data.model.Repository
import retrofit2.Call

class GithubFetcher(var githubApi: GithubApi) {

    fun searchRepositories(query: String): Call<SearchItems> {
        return githubApi.searchRepositories(query)
    }

    fun getAllPublicRepositories(): Call<List<Repository>> {
        return githubApi.getAllPublicRepositories()
    }
}