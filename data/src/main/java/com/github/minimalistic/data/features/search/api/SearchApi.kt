package com.github.minimalistic.data.features.search.api

import io.reactivex.Single
import com.github.minimalistic.data.features.search.dto.SearchResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchApi {

    @GET("users/{name}")
    fun getUserByName(@Path("name") name: String): Single<SearchResultDto>
}