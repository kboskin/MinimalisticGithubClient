package com.github.minimalistic.domain.features.search.repository

import io.reactivex.Single
import com.github.minimalistic.domain.features.search.model.SearchModel

interface SearchRepository {

    fun getUserByName(name: String): Single<SearchModel>
}