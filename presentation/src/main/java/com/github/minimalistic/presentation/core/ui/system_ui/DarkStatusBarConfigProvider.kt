package com.github.minimalistic.presentation.core.ui.system_ui

import com.github.minimalistic.presentation.R

object DarkStatusBarConfigProvider : StatusBarConfigProvider {
    override var drawUnderStatusBar = true
    override val statusBarColor: Int = R.color.color_status_bar_dark
    override val lightStatusBar: Boolean = true
}