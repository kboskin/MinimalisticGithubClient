package com.github.minimalistic.presentation.features.dispay_user_info.mapper

import com.nullgr.core.adapter.items.ListItem
import com.github.minimalistic.common.mapper.Mapper
import com.github.minimalistic.domain.features.repos.model.RepoModel
import com.github.minimalistic.presentation.features.dispay_user_info.ui.adapter.item.RepoItem
import javax.inject.Inject

class UserInfoUIMapper @Inject constructor(): Mapper<List<RepoModel>, List<ListItem>> {
    override fun mapFromObject(source: List<RepoModel>): List<ListItem> =
        source.map {
            RepoItem(it.repoName, it.language, it.stars)
        }
}