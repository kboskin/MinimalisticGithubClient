package com.github.minimalistic.data.di

import dagger.Binds
import dagger.Module
import com.github.minimalistic.common.mapper.Mapper
import com.github.minimalistic.data.features.repos.dto.ReposResultDto
import com.github.minimalistic.data.features.repos.mapper.ReposDtoToDomainMapper
import com.github.minimalistic.data.features.search.dto.SearchResultDto
import com.github.minimalistic.data.features.search.mapper.SearchDtoToDomainMapper
import com.github.minimalistic.domain.features.repos.model.RepoModel
import com.github.minimalistic.domain.features.search.model.SearchModel
import javax.inject.Singleton

@Module
@Suppress("UnnecessaryAbstractClass", "TooManyFunctions")
abstract class MappersModule {

    @Binds
    @Singleton
    abstract fun bindSearchToDomainMapper(
        mapper: SearchDtoToDomainMapper
    ): Mapper<SearchResultDto, SearchModel>

    @Binds
    @Singleton
    abstract fun bindReposToDomainMapper(
        mapper: ReposDtoToDomainMapper
    ): Mapper<ReposResultDto, RepoModel>
}