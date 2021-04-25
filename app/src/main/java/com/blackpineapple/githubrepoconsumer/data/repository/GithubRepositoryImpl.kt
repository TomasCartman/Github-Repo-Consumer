package com.blackpineapple.githubrepoconsumer.data.repository

import com.blackpineapple.githubrepoconsumer.api.GithubFetcher
import com.blackpineapple.githubrepoconsumer.data.model.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class GithubRepositoryImpl : GithubRepository {
    @Inject lateinit var githubFetcher: GithubFetcher

    @ExperimentalCoroutinesApi
    override fun getPublicRepositories() = callbackFlow<Result<List<Repository>>> {
        githubFetcher.getAllPublicRepositories().enqueue(object : Callback<List<Repository>>{
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                Timber.d(call.toString())
                //sendBlocking()
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Timber.d(call.toString())
                //TODO("Not yet implemented")
            }
        })
    }

    override fun searchForRepository(query: String) = callbackFlow<Result<List<Repository>>> {
        githubFetcher.searchRepositories(query).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                Timber.d(call.toString())
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Timber.d(call.toString())
                //TODO("Not yet implemented")
            }

        })
    }
}