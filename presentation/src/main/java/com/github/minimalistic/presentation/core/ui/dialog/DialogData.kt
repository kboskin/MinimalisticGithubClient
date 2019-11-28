package com.github.minimalistic.presentation.core.ui.dialog

interface DialogData {
    val title: String
    val message: String
    val negative: String?
    val positive: String?
}