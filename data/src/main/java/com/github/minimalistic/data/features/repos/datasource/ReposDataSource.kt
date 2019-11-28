package com.github.minimalistic.data.features.repos.datasource

import io.reactivex.Single
import com.github.minimalistic.data.features.repos.dto.ReposResultDto

interface ReposDataSource {
    fun getUserRepos(name: String): Single<List<ReposResultDto>>
}