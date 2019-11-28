package com.github.minimalistic.domain.features.repos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoModel(
    val repoName: String,
    val description: String?,
    val stars: Int,
    val watchers_count: Int,
    val language: String
): Parcelable