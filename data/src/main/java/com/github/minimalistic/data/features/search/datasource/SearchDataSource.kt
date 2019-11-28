package com.github.minimalistic.data.features.search.datasource

import io.reactivex.Single
import com.github.minimalistic.data.features.search.dto.SearchResultDto

interface SearchDataSource {
    fun getUserByName(name: String): Single<SearchResultDto>
}