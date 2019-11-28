package com.github.minimalistic.presentation.core.ui.system_ui

interface StatusBarConfigProvider {

    val statusBarColor: Int
    val lightStatusBar: Boolean
    var drawUnderStatusBar: Boolean
}