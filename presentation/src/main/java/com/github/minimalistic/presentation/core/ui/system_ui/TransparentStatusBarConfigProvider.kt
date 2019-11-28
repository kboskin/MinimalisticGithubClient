package com.github.minimalistic.presentation.core.ui.system_ui

object TransparentStatusBarConfigProvider : StatusBarConfigProvider {
    override var drawUnderStatusBar = true
    override val statusBarColor: Int = android.R.color.transparent
    override val lightStatusBar: Boolean = false
}