package com.github.minimalistic.presentation.features.dispay_user_info.ui

import com.nullgr.core.adapter.AdapterDelegate
import com.nullgr.core.adapter.AdapterDelegatesFactory
import com.nullgr.core.adapter.items.ListItem
import com.github.minimalistic.presentation.features.dispay_user_info.ui.adapter.delegate.RepoDelegate
import com.github.minimalistic.presentation.features.dispay_user_info.ui.adapter.item.RepoItem
import javax.inject.Inject

class DisplayUserInfoDelegatesFactory @Inject constructor() : AdapterDelegatesFactory {

    override fun createDelegate(clazz: Class<ListItem>): AdapterDelegate =
        when (clazz) {
            RepoItem::class.java -> RepoDelegate()
            else -> throw IllegalArgumentException("No delegate defined for ${clazz.simpleName}")
        }
}