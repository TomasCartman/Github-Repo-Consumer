package com.blackpineapple.githubrepoconsumer.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    var ownerName: String,
    @SerializedName("avatar_url")
    var ownerAvatarUrl: String
)