package com.github.minimalistic.data.features.repos.dto

import com.google.gson.annotations.SerializedName

data class ReposResultDto(
    @SerializedName("name")
    val repoName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("stargazers_count")
    val stars: Int,
    @SerializedName("watchers_count")
    val watchers_count: Int,
    @SerializedName("language")
    val language: String
)