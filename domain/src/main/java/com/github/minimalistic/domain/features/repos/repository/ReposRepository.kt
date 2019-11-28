package com.github.minimalistic.domain.features.repos.repository

import io.reactivex.Single
import com.github.minimalistic.domain.features.repos.model.RepoModel

interface ReposRepository{
    fun getUserRepos(name: String): Single<List<RepoModel>>
}