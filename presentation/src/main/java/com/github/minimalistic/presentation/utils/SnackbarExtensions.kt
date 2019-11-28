package com.github.minimalistic.presentation.utils

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import com.google.android.material.snackbar.Snackbar
import com.github.minimalistic.presentation.core.pm.widgets.SnackBarControl
import com.github.minimalistic.presentation.core.ui.snack_bar_view.SnackBarData

fun Snackbar.applyMessageTextAppearance(@StyleRes style: Int): Snackbar {
    with(view) {
        val textView = findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView?.let {
            TextViewCompat.setTextAppearance(it, style)
            it.maxLines = Int.MAX_VALUE
        }
    }
    return this
}

fun Snackbar.applyActionTextAppereance(@StyleRes style: Int): Snackbar {
    with(view) {
        val textView = findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
        textView?.let {
            TextViewCompat.setTextAppearance(it, style)
            it.isAllCaps = false
            it.maxLines = Int.MAX_VALUE
        }
    }
    return this
}

fun Snackbar.applySnackbarHeight(): Snackbar {
    with(view) {
        // TODO: set snackbar height from project dimens
//        layoutParams?.height = view.resources.getDimensionPixelSize(android.R.dimen.dialog_fixed_height_major)
    }
    return this
}

fun Snackbar.applyTextDrawable(@DrawableRes drawable: Int?): Snackbar {
    drawable?.let { nonNullDrawable ->
        with(view) {
            val textView = findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView?.let {
                it.compoundDrawablePadding = paddingLeft
                it.setPadding(0, 0, 0, 0)
                it.setCompoundDrawablesWithIntrinsicBounds(nonNullDrawable, 0, 0, 0)
            }
        }
    }
    return this
}

fun Snackbar.applyBackgroundColor(@ColorRes color: Int): Snackbar {
    with(view) {
        setBackgroundResource(color)
    }
    return this
}

fun Snackbar.applyBackground(@DrawableRes drawable: Int): Snackbar {
    view.setBackgroundResource(drawable)
    return this
}

fun makeSnackBar(view: View, data: SnackBarData): Snackbar =
    Snackbar.make(view, data.message, data.length)
        .applySnackbarHeight()
        /*.applyBackground(R.drawable.bg_snackbar)
        .applyMessageTextAppearance(R.style.Typography_SubTitle5_WithSpacings_White)
        .applyActionTextAppereance(R.style.Typography_SubTitle4_WithSpacings_ActiveGreen)*/
        .applyTextDrawable(data.icon)

fun makeSnackBarWithAction(
    view: View,
    data: SnackBarData,
    control: SnackBarControl<SnackBarData>
): Snackbar =
    makeSnackBar(view, data)
        .setAction(data.button) {
            control.sendResult()
        }
