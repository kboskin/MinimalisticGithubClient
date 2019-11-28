package com.github.minimalistic.presentation.widgets

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class GridSpacingItemDecoration(
    context: Context,
    private val spanCount: Int,
    @DimenRes private val margin: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    private val spacing by lazy { getPixelSize(context.resources, margin) }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * spacing / spanCount

            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }

    private fun getPixelSize(resources: Resources, @DimenRes margin: Int): Int =
        if (margin == 0) 0 else resources.getDimensionPixelSize(margin)
}