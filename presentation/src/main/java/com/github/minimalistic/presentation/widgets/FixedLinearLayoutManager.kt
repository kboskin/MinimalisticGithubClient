package com.github.minimalistic.presentation.widgets

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

@SuppressLint("WrongConstant")
class FixedLinearLayoutManager(
    context: Context?,
    orientation: Int = VERTICAL,
    reverseLayout: Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout) {

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            // this is ugly solution to fix bug with inconsistency detected
            Timber.i("Inconsistency detected. Invalid view holder adapter.")
        }
    }
}