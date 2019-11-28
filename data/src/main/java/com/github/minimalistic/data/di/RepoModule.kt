package com.github.minimalistic.data.di

import dagger.Binds
import dagger.Module
import com.github.minimalistic.data.features.repos.repository.ReposDataRepository
import com.github.minimalistic.data.features.search.repository.SearchDataRepository
import com.github.minimalistic.domain.features.repos.repository.ReposRepository
import com.github.minimalistic.domain.features.search.repository.SearchRepository
import javax.inject.Singleton

@Module
@Suppress("TooManyFunctions")
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun bindSearchRepository(repo: SearchDataRepository): SearchRepository

    @Binds
    @Singleton
    abstract fun bindRepoRepository(repo: ReposDataRepository): ReposRepository
}