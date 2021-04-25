package com.blackpineapple.githubrepoconsumer.injects

import com.blackpineapple.githubrepoconsumer.api.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun providerGithubApi(): GithubApi {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(GithubApi::class.java)
    }

}