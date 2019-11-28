package com.github.minimalistic.presentation.features.search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.github.minimalistic.domain.features.repos.model.RepoModel
import com.github.minimalistic.domain.features.search.model.SearchModel

@Parcelize
data class UserWithReposModel(
    val searchModel: SearchModel,
    val repos: List<RepoModel>
): Parcelable