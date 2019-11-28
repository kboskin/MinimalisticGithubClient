package com.github.minimalistic.data.features.search.repository

import io.reactivex.Single
import com.github.minimalistic.common.di.qualifires.Remote
import com.github.minimalistic.common.mapper.Mapper
import com.github.minimalistic.data.features.search.datasource.SearchDataSource
import com.github.minimalistic.data.features.search.dto.SearchResultDto
import com.github.minimalistic.domain.features.search.model.SearchModel
import com.github.minimalistic.domain.features.search.repository.SearchRepository
import javax.inject.Inject

class SearchDataRepository @Inject constructor(
    @Remote
    private val searchRemoteDataSource: SearchDataSource,
    private val toDomainMapper: Mapper<SearchResultDto, SearchModel>
) : SearchRepository {
    override fun getUserByName(name: String): Single<SearchModel> =
        searchRemoteDataSource.getUserByName(name).map(toDomainMapper::mapFromObject)
}