package com.github.minimalistic.data.features.repos.repository

import io.reactivex.Single
import com.github.minimalistic.common.di.qualifires.Remote
import com.github.minimalistic.common.mapper.Mapper
import com.github.minimalistic.data.features.repos.datasource.ReposDataSource
import com.github.minimalistic.data.features.repos.dto.ReposResultDto
import com.github.minimalistic.domain.features.repos.model.RepoModel
import com.github.minimalistic.domain.features.repos.repository.ReposRepository
import javax.inject.Inject

class ReposDataRepository @Inject constructor(
    @Remote
    private val remoteDataSource: ReposDataSource,
    private val toDomainMapper: Mapper<ReposResultDto, RepoModel>
) : ReposRepository {
    override fun getUserRepos(name: String): Single<List<RepoModel>> =
        remoteDataSource.getUserRepos(name).map(toDomainMapper::mapFromObjects)
}
