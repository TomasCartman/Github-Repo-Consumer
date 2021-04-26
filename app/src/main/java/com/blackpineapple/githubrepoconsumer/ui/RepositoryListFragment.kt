package com.blackpineapple.githubrepoconsumer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.blackpineapple.githubrepoconsumer.R
import com.blackpineapple.githubrepoconsumer.viewmodels.RepositoryListFragmentViewModel


class RepositoryListFragment : Fragment() {
    private lateinit var viewModel: RepositoryListFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.NewInstanceFactory()
                .create(RepositoryListFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_repository_list, container, false)

        viewModel.getPublicRepositories()

        return view
    }
}