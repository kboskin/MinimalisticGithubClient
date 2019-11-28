package com.github.minimalistic.presentation.core.ui.system_ui

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.github.minimalistic.presentation.R

object LightStatusBarConfigProvider : StatusBarConfigProvider {
    override var drawUnderStatusBar = true
    override val statusBarColor: Int
        get() = if (VERSION.SDK_INT >= VERSION_CODES.M) R.color.color_status_bar_light
        else R.color.color_background_status_bar_light
    override val lightStatusBar: Boolean = false
}