package com.github.minimalistic.presentation.features.app.pm

import com.github.minimalistic.presentation.Screens
import com.github.minimalistic.presentation.core.pm.ServiceFacade
import com.github.minimalistic.presentation.core.pm.listeners.ConnectionListener
import com.github.minimalistic.presentation.core.pm.BasePm
import javax.inject.Inject

class AppPm @Inject constructor(
    services: ServiceFacade
) : BasePm(services), ConnectionListener {

    val coldStartAction = Action<Unit>()

    override fun onCreate() {
        super.onCreate()

        coldStartAction.observable
            .doOnNext { router.newRootFlow(Screens.MainFlow) }
            .retry()
            .subscribe()
            .untilDestroy()
    }
}