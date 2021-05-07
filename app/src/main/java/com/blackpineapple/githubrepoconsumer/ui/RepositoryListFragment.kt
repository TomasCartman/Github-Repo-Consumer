package com.blackpineapple.githubrepoconsumer.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blackpineapple.githubrepoconsumer.R
import com.blackpineapple.githubrepoconsumer.data.adapters.RepositoryListAdapter
import com.blackpineapple.githubrepoconsumer.databinding.FragmentRepositoryListBinding
import com.blackpineapple.githubrepoconsumer.viewmodels.RepositoryListFragmentViewModel
import com.google.android.material.appbar.MaterialToolbar


class RepositoryListFragment : Fragment() {
    private lateinit var binding: FragmentRepositoryListBinding
    private lateinit var viewModel: RepositoryListFragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var toolbar: MaterialToolbar

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

        progressBar = binding.progressBar
        toolbar = binding.toolbar

        updateRecyclerView()
        setToolbarAction()

        return binding.root
    }

    private fun updateRecyclerView() {
        viewModel.repositoryListLiveData.observe(this.viewLifecycleOwner, Observer {
            recyclerView.apply {
                adapter = RepositoryListAdapter(it)
                visibility = View.VISIBLE
            }
            progressBar.visibility = View.GONE
        })
    }

    private fun setToolbarAction() {
        val searchItem: MenuItem = toolbar.menu.findItem(R.id.search_menu)
        val searchView = searchItem.actionView as SearchView

        searchView.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query != null) {
                        viewModel.searchRepository(query)
                        searchItem.collapseActionView()
                        searchView.clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.isNullOrBlank()) {
                        viewModel.getPublicRepositories()
                    }
                    return true
                }
            })
        }
    }
}