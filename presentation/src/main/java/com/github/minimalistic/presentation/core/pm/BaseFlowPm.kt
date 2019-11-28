package com.github.minimalistic.presentation.core.pm

import androidx.annotation.CallSuper

abstract class BaseFlowPm(
    services: ServiceFacade
) : BasePm(services) {

    val launchScreenAction = Action<Unit>()

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        launchScreenAction
            .observable
            .doOnNext { navigateToLaunchScreen() }
            .subscribe()
            .untilDestroy()
    }

    abstract fun navigateToLaunchScreen()
}