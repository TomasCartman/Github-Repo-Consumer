package com.blackpineapple.githubrepoconsumer.di.koin

import com.blackpineapple.githubrepoconsumer.data.repository.GithubRepository
import com.blackpineapple.githubrepoconsumer.data.repository.GithubRepositoryImpl
import org.koin.dsl.module.module

object KoinModules {

    val githubRepositoryModule = module {
        single { GithubRepositoryImpl() as GithubRepository }
    }
}