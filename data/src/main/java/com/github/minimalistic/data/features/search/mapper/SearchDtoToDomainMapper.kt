package com.github.minimalistic.data.features.search.mapper

import com.github.minimalistic.common.mapper.Mapper
import com.github.minimalistic.data.features.search.dto.SearchResultDto
import com.github.minimalistic.domain.features.search.model.SearchModel
import javax.inject.Inject

class SearchDtoToDomainMapper @Inject constructor() : Mapper<SearchResultDto, SearchModel> {
    override fun mapFromObject(source: SearchResultDto): SearchModel =
        with(source) {
            SearchModel(
                login,
                id,
                photoUrl,
                followersCount,
                followingCount,
                company,
                name,
                reposPath
            )
        }

}