package com.github.minimalistic.domain.features.search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchModel(
    val login: String,
    val userId: String,
    val photoUrl: String,
    val followersCount: Int,
    val followingCount: Int,
    val company: String?,
    val name: String,
    val reposPath: String
): Parcelable