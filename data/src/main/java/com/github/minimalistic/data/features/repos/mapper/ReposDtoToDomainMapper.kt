package com.github.minimalistic.data.features.repos.mapper

import com.github.minimalistic.common.mapper.Mapper
import com.github.minimalistic.data.features.repos.dto.ReposResultDto
import com.github.minimalistic.domain.features.repos.model.RepoModel
import javax.inject.Inject

class ReposDtoToDomainMapper @Inject constructor() : Mapper<ReposResultDto, RepoModel> {
    override fun mapFromObject(source: ReposResultDto): RepoModel =
        with(source) {
            RepoModel(
                repoName,
                description,
                stars,
                watchers_count,
                language
            )
        }
}