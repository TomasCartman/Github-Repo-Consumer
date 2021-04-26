package com.blackpineapple.githubrepoconsumer.data.model

import com.google.gson.annotations.SerializedName

data class Repository (
        @SerializedName("name")
        var repoName: String,
        @SerializedName("description")
        var repoDescription: String,
        @SerializedName("owner")
        var owner: Owner
)