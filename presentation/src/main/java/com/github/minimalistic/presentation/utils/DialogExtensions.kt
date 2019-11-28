@file:Suppress("NOTHING_TO_INLINE")

package com.github.minimalistic.presentation.utils

import androidx.fragment.app.FragmentManager
import com.afollestad.materialdialogs.MaterialDialog
import com.github.minimalistic.presentation.widgets.dialogs.ProgressDialog
import io.reactivex.functions.Consumer

const val PROGRESS_TAG = "PROGRESS_TAG"

inline fun MaterialDialog.visibility(): Consumer<in Boolean> = Consumer {
    when (it) {
        true -> show()
        else -> dismiss()
    }
}

inline fun ProgressDialog.visibility(fragmentManager: FragmentManager): Consumer<in Boolean> = Consumer {
    val fragment = fragmentManager.findFragmentByTag(PROGRESS_TAG)
    if (fragment != null && !it) {
        (fragment as ProgressDialog).dismissAllowingStateLoss()
        fragmentManager.executePendingTransactions()
    } else if (fragment == null && it) {
        show(fragmentManager, PROGRESS_TAG)
        fragmentManager.executePendingTransactions()
    }
}