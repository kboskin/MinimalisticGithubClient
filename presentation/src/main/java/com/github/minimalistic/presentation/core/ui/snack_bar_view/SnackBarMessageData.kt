package com.github.minimalistic.presentation.core.ui.snack_bar_view

import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE

sealed class SnackBarMessageData(
    override val icon: Int? = null,
    override val message: String,
    override val button: String? = null,
    override val length: Int = LENGTH_SHORT
) : SnackBarData {

    class SimpleTextMessage(
        message: String,
        length: Int = LENGTH_SHORT
    ) : SnackBarMessageData(message = message, length = length)
}