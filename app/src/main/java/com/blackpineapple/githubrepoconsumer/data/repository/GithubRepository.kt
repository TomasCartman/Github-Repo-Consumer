package com.blackpineapple.githubrepoconsumer.data.repository

import com.blackpineapple.githubrepoconsumer.data.model.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun getPublicRepositories(): Flow<Result<List<Repository>>>

    fun searchForRepository(query: String): Flow<Result<List<Repository>>>
}