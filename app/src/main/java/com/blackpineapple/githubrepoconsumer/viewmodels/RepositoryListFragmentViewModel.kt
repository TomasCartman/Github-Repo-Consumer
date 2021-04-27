package com.blackpineapple.githubrepoconsumer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackpineapple.githubrepoconsumer.data.model.Repository
import com.blackpineapple.githubrepoconsumer.data.repository.GithubRepository
import com.blackpineapple.githubrepoconsumer.data.repository.GithubRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class RepositoryListFragmentViewModel : ViewModel() {
    private val githubRepository: GithubRepository = GithubRepositoryImpl()
    private val mutableRepositoryListLiveData = MutableLiveData<List<Repository>>()
    val repositoryListLiveData: LiveData<List<Repository>>
        get() = mutableRepositoryListLiveData

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
}