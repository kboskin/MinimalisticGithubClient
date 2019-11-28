package com.github.minimalistic.presentation.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nullgr.core.adapter.items
import com.nullgr.core.adapter.items.ListItem

inline fun <reified T : ListItem> RecyclerView.ViewHolder.withAdapterPosition(
    block: (items: List<ListItem>, item: T, position: Int) -> Unit
) {
    with(adapterPosition) {
        if (this != RecyclerView.NO_POSITION) {
            val items = items()
            if (items != null && this >= 0 && this < items.size) {
                block.invoke(items, items[this] as T, this)
            }
        }
    }
}

fun View.applyInsetsToContentView(fitsSystemWindows: Boolean) {
    this.fitsSystemWindows = fitsSystemWindows
    ViewCompat.requestApplyInsets(this)
}

fun View.applyWindowInsetsForChildrenView() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val params = v.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = insets.systemWindowInsetTop
        insets.consumeSystemWindowInsets()
    }
}

fun ImageView.loadImage(
    url: String?,
    @DrawableRes loadingPlaceholder: Int,
    @DrawableRes errorPlaceholder: Int
) {
    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions.noTransformation()
                .placeholder(loadingPlaceholder)
                .error(errorPlaceholder)
        )
        .into(this)
}