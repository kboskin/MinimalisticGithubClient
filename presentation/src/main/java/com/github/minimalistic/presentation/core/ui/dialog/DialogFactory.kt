package com.github.minimalistic.presentation.core.ui.dialog

import android.app.Activity
import android.app.Dialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import me.dmdev.rxpm.widget.DialogControl
import com.github.minimalistic.presentation.R

fun createDialog(
    fragment: Fragment,
    dc: DialogControl<DialogData, DialogResult>,
    data: DialogData
) = createDialog(checkNotNull(fragment.activity), dc, data)

fun createDialog(
    activity: Activity,
    dc: DialogControl<DialogData, DialogResult>,
    data: DialogData
): Dialog =
    MaterialDialog.Builder(activity)
        .cancelable(false)
        .title(data.title)
        .content(data.message)
        .positiveColor(ContextCompat.getColor(activity, R.color.colorAccent))
        .titleColor(ContextCompat.getColor(activity, R.color.black))
        .buttons(dc, data)
        .build()

fun MaterialDialog.Builder.buttons(
    dc: DialogControl<DialogData, DialogResult>,
    data: DialogData
): MaterialDialog.Builder =
    this.also { builder ->
        data.negative?.let { text ->
            builder
                .negativeText(text)
                .onNegative { _, _ -> dc.sendResult(DialogResult.NEGATIVE) }
        }
        data.positive?.let { text ->
            builder
                .positiveText(text)
                .onPositive { _, _ -> dc.sendResult(DialogResult.POSITIVE) }
        }
    }
