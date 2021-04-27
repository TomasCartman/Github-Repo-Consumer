package com.blackpineapple.githubrepoconsumer.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blackpineapple.githubrepoconsumer.R
import com.blackpineapple.githubrepoconsumer.data.model.Repository

class RepositoryListAdapter(private val repoList: List<Repository>)
    : RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repoList[position]

        holder.repositoryNameTextView.text = repository.repoName
        holder.ownerNameTextView.text = repository.owner.ownerName
    }

    override fun getItemCount(): Int = repoList.size

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repositoryNameTextView: TextView = itemView.findViewById(R.id.repository_name_textView)
        val ownerNameTextView: TextView = itemView.findViewById(R.id.owner_name_textView)
    }
}