package com.blackpineapple.githubrepoconsumer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blackpineapple.githubrepoconsumer.data.adapters.RepositoryListAdapter
import com.blackpineapple.githubrepoconsumer.databinding.FragmentRepositoryListBinding
import com.blackpineapple.githubrepoconsumer.viewmodels.RepositoryListFragmentViewModel


class RepositoryListFragment : Fragment() {
    private lateinit var binding: FragmentRepositoryListBinding
    private lateinit var viewModel: RepositoryListFragmentViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.NewInstanceFactory()
                .create(RepositoryListFragmentViewModel::class.java)
        viewModel.getPublicRepositories()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRepositoryListBinding.inflate(inflater, container, false)

        recyclerView = binding.fragmentRepositoryRecyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = RepositoryListAdapter(emptyList())
        }

        updateRecyclerView()

        return binding.root
    }

    private fun updateRecyclerView() {
        viewModel.repositoryListLiveData.observe(this.viewLifecycleOwner, {
            recyclerView.adapter = RepositoryListAdapter(it)
        })
    }
}