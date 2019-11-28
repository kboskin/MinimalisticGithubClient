package com.github.minimalistic.presentation.core.navigation.screens

import androidx.fragment.app.DialogFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

abstract class BottomSheetScreen : SupportAppScreen() {
    abstract override fun getFragment(): DialogFragment
}