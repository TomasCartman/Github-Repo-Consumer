package com.blackpineapple.githubrepoconsumer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackpineapple.githubrepoconsumer.data.model.Repository
import com.blackpineapple.githubrepoconsumer.data.repository.GithubRepository
import com.blackpineapple.githubrepoconsumer.data.repository.GithubRepositoryImpl
import com.blackpineapple.githubrepoconsumer.di.koin.KoinModules.githubRepositoryModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.inject
import timber.log.Timber

class RepositoryListFragmentViewModel : ViewModel(), KoinComponent {
    private val githubRepository: GithubRepository by inject()
    private val mutableRepositoryListLiveData = MutableLiveData<List<Repository>>()
    val repositoryListLiveData: LiveData<List<Repository>>
        get() = mutableRepositoryListLiveData

    init {
        startKoin(listOf(githubRepositoryModule))
    }

    fun getPublicRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getPublicRepositories().collect {
                when {
                    it.isSuccess -> {
                        val repoList = it.getOrNull()
                        if (repoList != null) {
                            mutableRepositoryListLiveData.postValue(repoList)
                        }
                    }
                    it.isFailure -> {
                        Timber.d(it.exceptionOrNull())
                    }
                }
            }
        }
    }

    fun searchRepository(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            githubRepository.searchForRepository(query).collect {
                when {
                    it.isSuccess -> {
                        val repoList = it.getOrNull()
                        if (repoList != null) {
                            mutableRepositoryListLiveData.postValue(repoList)
                        }
                    }
                    it.isFailure -> {
                        Timber.d(it.exceptionOrNull())
                    }
                }
            }
        }
    }
}