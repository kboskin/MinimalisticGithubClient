package com.github.minimalistic.presentation.features.flow.pm

import com.github.minimalistic.presentation.Screens
import com.github.minimalistic.presentation.core.pm.BaseFlowPm
import com.github.minimalistic.presentation.core.pm.ServiceFacade
import javax.inject.Inject

class MainFlowPm @Inject constructor(
    services: ServiceFacade
) : BaseFlowPm(services) {

    override fun navigateToLaunchScreen() {
        router.newRootScreen(Screens.SearchScreen)
    }
}