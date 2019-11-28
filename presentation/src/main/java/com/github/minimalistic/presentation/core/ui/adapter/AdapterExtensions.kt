package com.github.minimalistic.presentation.core.ui.adapter

import com.nullgr.core.adapter.items.ListItem

@Suppress("ReturnCount")
fun <T : ListItem> List<T>.isChanged(other: List<T>): Boolean {
    if (size != other.size) {
        return true
    }

    forEachIndexed { index, item ->
        if (!item.areItemsTheSame(other[index])) {
            return true
        } else if (!item.areContentsTheSame(other[index])) {
            return true
        }
    }

    return false
}