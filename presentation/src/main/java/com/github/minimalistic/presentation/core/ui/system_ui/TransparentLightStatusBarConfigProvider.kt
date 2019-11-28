package com.github.minimalistic.presentation.core.ui.system_ui

object TransparentLightStatusBarConfigProvider : StatusBarConfigProvider {
    override var drawUnderStatusBar = true
    override val statusBarColor: Int = android.R.color.transparent
    override val lightStatusBar: Boolean = true
}