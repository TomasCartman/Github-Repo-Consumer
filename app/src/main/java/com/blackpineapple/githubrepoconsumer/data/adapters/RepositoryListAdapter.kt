package com.blackpineapple.githubrepoconsumer.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.blackpineapple.githubrepoconsumer.R
import com.blackpineapple.githubrepoconsumer.data.model.Repository
import com.blackpineapple.githubrepoconsumer.ui.RepositoryDetailFragment
import timber.log.Timber

class RepositoryListAdapter(private val repoList: List<Repository>)
    : RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repoList[position]

        Timber.d(repository.owner.ownerName)
        Timber.d(repository.repoDescription)

        holder.repositoryNameTextView.text = repository.repoName
        holder.ownerNameTextView.text = repository.owner.ownerName
        if(repository.repoDescription != null) {
            holder.repositoryDescription = repository.repoDescription
        }
        holder.ownerAvatarUrl = repository.owner.ownerAvatarUrl

    }

    override fun getItemCount(): Int = repoList.size


    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val repositoryNameTextView: TextView = itemView.findViewById(R.id.repository_name_textView)
        val ownerNameTextView: TextView = itemView.findViewById(R.id.owner_name_textView)
        var repositoryDescription: String = ""
        var ownerAvatarUrl: String = ""

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val activity = itemView.context as AppCompatActivity
            val fragment = RepositoryDetailFragment.newInstance(
                    repositoryNameTextView.text.toString(),
                    repositoryDescription,
                    ownerNameTextView.text.toString(),
                    ownerAvatarUrl
            )
            activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .addToBackStack(null).commit()
        }
    }

}