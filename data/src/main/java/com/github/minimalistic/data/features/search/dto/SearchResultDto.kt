package com.github.minimalistic.data.features.search.dto

import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: String,
    @SerializedName("avatar_url") val photoUrl: String,
    @SerializedName("followers") val followersCount: Int,
    @SerializedName("following") val followingCount: Int,
    @SerializedName("company") val company: String?,
    @SerializedName("name") val name: String,
    @SerializedName("repos_url") val reposPath: String
)