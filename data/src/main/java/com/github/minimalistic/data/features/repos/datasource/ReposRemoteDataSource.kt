package com.github.minimalistic.data.features.repos.datasource

import io.reactivex.Single
import com.github.minimalistic.common.di.qualifires.Remote
import com.github.minimalistic.data.features.repos.api.ReposApi
import com.github.minimalistic.data.features.repos.dto.ReposResultDto
import javax.inject.Inject

@Remote
class ReposRemoteDataSource @Inject constructor(
    private val api: ReposApi
): ReposDataSource {
    override fun getUserRepos(name: String): Single<List<ReposResultDto>> =
        api.getUserByName(name)
}