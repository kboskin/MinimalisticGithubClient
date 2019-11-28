package com.github.minimalistic.presentation.core.pm.factory

import me.dmdev.rxpm.PresentationModel

interface PmFactory {
    fun <T : PresentationModel> createViewModel(modelClass: Class<T>): T
}