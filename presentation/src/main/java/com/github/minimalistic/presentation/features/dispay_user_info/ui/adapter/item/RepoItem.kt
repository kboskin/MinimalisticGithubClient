package com.github.minimalistic.presentation.features.dispay_user_info.ui.adapter.item

import com.nullgr.core.adapter.items.ListItem

data class RepoItem(
    val name: String,
    val language: String?,
    val stars: Int
): ListItem