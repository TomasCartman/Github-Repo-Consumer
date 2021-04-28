package com.blackpineapple.githubrepoconsumer.api

import com.blackpineapple.githubrepoconsumer.data.model.Repository

data class SearchItems(
        val total_count: Int,
        val items: List<Repository>
)