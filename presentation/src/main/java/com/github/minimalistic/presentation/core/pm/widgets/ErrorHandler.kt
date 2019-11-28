package com.github.minimalistic.presentation.core.pm.widgets

import com.github.minimalistic.common.errors.NetworkErrors
import com.github.minimalistic.presentation.R
import com.github.minimalistic.presentation.core.pm.BasePm
import com.github.minimalistic.presentation.core.ui.snack_bar_view.SnackBarMessageData

class ErrorHandler(private val pm: BasePm) {

    fun handleError(error: Throwable) {

        val titleRes = when (error) {
            is NetworkErrors.ConnectionError -> R.string.error_network_title
            is NetworkErrors.NotFoundError -> R.string.error_not_found
            else -> {
                error.printStackTrace()
                R.string.error_retry
            }
        }
        val snackBarData = SnackBarMessageData.SimpleTextMessage(
            pm.resources.getString(titleRes)
        )

        pm.snackBarControl.show(snackBarData)
    }
}

fun BasePm.errorHandler() = ErrorHandler(this)