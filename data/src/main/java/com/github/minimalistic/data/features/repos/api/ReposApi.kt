package com.github.minimalistic.data.features.repos.api

import io.reactivex.Single
import com.github.minimalistic.data.features.repos.dto.ReposResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposApi {

    @GET("users/{name}/repos")
    fun getUserByName(@Path("name") name: String): Single<List<ReposResultDto>>
}