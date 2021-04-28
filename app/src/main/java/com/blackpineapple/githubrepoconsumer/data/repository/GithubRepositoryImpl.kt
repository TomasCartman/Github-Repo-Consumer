package com.blackpineapple.githubrepoconsumer.data.repository

import com.blackpineapple.githubrepoconsumer.api.GithubApi
import com.blackpineapple.githubrepoconsumer.api.GithubFetcher
import com.blackpineapple.githubrepoconsumer.api.SearchItems
import com.blackpineapple.githubrepoconsumer.data.model.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class GithubRepositoryImpl: GithubRepository {
    private val githubFetcher: GithubFetcher

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        githubFetcher = GithubFetcher(retrofit.create(GithubApi::class.java))
    }


    @ExperimentalCoroutinesApi
    override fun getPublicRepositories() = callbackFlow<Result<List<Repository>>> {
        val callback = object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                response.body()?.let {
                    val repoList: List<Repository> = it
                    sendBlocking(Result.success(repoList))
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
               sendBlocking(Result.failure(t))
            }
        }

        githubFetcher.getAllPublicRepositories().enqueue(callback)

        awaitClose {
            githubFetcher.getAllPublicRepositories().cancel()
        }
    }

    @ExperimentalCoroutinesApi
    override fun searchForRepository(query: String) = callbackFlow<Result<List<Repository>>> {
        val callback = object : Callback<SearchItems> {
            override fun onResponse(call: Call<SearchItems>, response: Response<SearchItems>) {
               response.body()?.let {
                   Timber.d(it.toString())
                   val repoList = it.items
                   sendBlocking(Result.success(repoList))
               }
            }

            override fun onFailure(call: Call<SearchItems>, t: Throwable) {
                Timber.d(call.toString())
                sendBlocking(Result.failure(t))
            }

        }

        githubFetcher.searchRepositories(query).enqueue(callback)

        awaitClose {
            githubFetcher.searchRepositories(query).cancel()
        }
    }
}