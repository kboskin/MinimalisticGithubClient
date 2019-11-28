package com.github.minimalistic.data.di

import dagger.Module
import dagger.Provides
import com.github.minimalistic.data.features.repos.api.MockedReposApi
import com.github.minimalistic.data.features.repos.api.ReposApi
import com.github.minimalistic.data.features.search.api.MockedSearchApi
import com.github.minimalistic.data.features.search.api.SearchApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@Suppress("FunctionOnlyReturningConstant", "TooManyFunctions")
class ApiModule {

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi = when (ApiConfig.USE_MOCKED_SEARCH_API) {
        true -> MockedSearchApi()
        else -> retrofit.create(SearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReposApi(retrofit: Retrofit): ReposApi = when (ApiConfig.USE_MOCKED_REPOS_API) {
        true -> MockedReposApi()
        else -> retrofit.create(ReposApi::class.java)
    }


    private object ApiConfig {
        const val USE_MOCKED_SEARCH_API = false
        const val USE_MOCKED_REPOS_API = false
    }
}