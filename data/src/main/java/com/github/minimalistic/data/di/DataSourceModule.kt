package com.github.minimalistic.data.di

import dagger.Binds
import dagger.Module
import com.github.minimalistic.common.di.qualifires.Remote
import com.github.minimalistic.data.features.repos.datasource.ReposDataSource
import com.github.minimalistic.data.features.repos.datasource.ReposRemoteDataSource
import com.github.minimalistic.data.features.search.datasource.SearchDataSource
import com.github.minimalistic.data.features.search.datasource.SearchRemoteDataSource
import javax.inject.Singleton

@Suppress("TooManyFunctions")
@Module
abstract class DataSourceModule {

    @Remote
    @Binds
    @Singleton
    abstract fun bindReposRemoteDataSource(source: ReposRemoteDataSource): ReposDataSource

    @Remote
    @Binds
    @Singleton
    abstract fun bindSearchRemoteDataSource(source: SearchRemoteDataSource): SearchDataSource
}