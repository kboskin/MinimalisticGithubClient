package com.github.minimalistic.data.features.search.datasource

import io.reactivex.Single
import com.github.minimalistic.common.di.qualifires.Remote
import com.github.minimalistic.data.features.search.api.SearchApi
import com.github.minimalistic.data.features.search.dto.SearchResultDto
import javax.inject.Inject

@Remote
class SearchRemoteDataSource @Inject constructor(
    private val api: SearchApi
): SearchDataSource{
    override fun getUserByName(name: String): Single<SearchResultDto> = api.getUserByName(name)
}