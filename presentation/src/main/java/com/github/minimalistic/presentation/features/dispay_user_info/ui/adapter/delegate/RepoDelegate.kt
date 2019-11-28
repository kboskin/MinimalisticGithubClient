package com.github.minimalistic.presentation.features.dispay_user_info.ui.adapter.delegate

import androidx.recyclerview.widget.RecyclerView
import com.nullgr.core.adapter.items.ListItem
import com.nullgr.core.adapter.ktx.AdapterDelegate
import com.nullgr.core.adapter.ktx.ViewHolder
import kotlinx.android.synthetic.main.item_repo.*
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.features.dispay_user_info.ui.adapter.item.RepoItem

class RepoDelegate() : AdapterDelegate() {
    override val layoutResource: Int = R.layout.item_repo
    override val itemType: Any = RepoItem::class
    
    override fun onBindViewHolder(items: List<ListItem>, position: Int, holder: RecyclerView.ViewHolder) {
        val item = items[position] as RepoItem

        with(holder as ViewHolder) {
            repoNameTextView.text = item.name
            repoStarCountTextView.text = item.stars.toString()
            repoLangTextView.text = item.language ?: holder.containerView.context.getString(R.string.repo_language_not_detected)
            
        }
    }
}