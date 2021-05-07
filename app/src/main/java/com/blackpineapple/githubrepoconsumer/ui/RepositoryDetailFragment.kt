package com.blackpineapple.githubrepoconsumer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blackpineapple.githubrepoconsumer.databinding.FragmentRepositoryDetailBinding
import com.bumptech.glide.Glide

class RepositoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentRepositoryDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)

        val repoName = arguments?.getString(ARG_REPO_NAME) as String
        val repoDescription = arguments?.getString(ARG_REPO_DESCRIPTION) as String
        val ownerName = arguments?.getString(ARG_OWNER_NAME) as String
        val ownerAvatarUrl = arguments?.getString(ARG_OWNER_AVATAR_URL) as String

        binding.repoNameTextView.text = repoName
        binding.repoDescriptionTextView.text = repoDescription
        binding.ownerTextView.text = ownerName

        context?.let {
            Glide.with(it)
                    .load(ownerAvatarUrl)
                    .circleCrop()
                    .into(binding.ownerImageView)
        }

        return binding.root
    }

    companion object {
        private const val ARG_REPO_NAME = "repoName"
        private const val ARG_REPO_DESCRIPTION = "repoDescription"
        private const val ARG_OWNER_NAME = "ownerName"
        private const val ARG_OWNER_AVATAR_URL = "ownerAvatarUrl"


        fun newInstance(repoName: String, repoDescription: String, ownerName: String, ownerAvatarUrl: String)
        : RepositoryDetailFragment {
            val args = Bundle().apply {
                putString(ARG_REPO_NAME, repoName)
                putString(ARG_REPO_DESCRIPTION, repoDescription)
                putString(ARG_OWNER_NAME, ownerName)
                putString(ARG_OWNER_AVATAR_URL, ownerAvatarUrl)
            }
            return RepositoryDetailFragment().apply { arguments = args }
        }
    }
}