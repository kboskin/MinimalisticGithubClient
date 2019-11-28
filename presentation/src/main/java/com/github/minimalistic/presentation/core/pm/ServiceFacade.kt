package com.github.minimalistic.presentation.core.pm

import com.nullgr.core.resources.ResourceProvider
import com.nullgr.core.rx.RxBus
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UseDataClass")
@Singleton
class ServiceFacade @Inject constructor(
    resourceProvider: ResourceProvider,
    networkFacade: ReactiveNetworkFacade,
    rxBus: RxBus,
    apiErrorParser: ExceptionParser
) {
    val resources: ResourceProvider = resourceProvider
    val network: ReactiveNetworkFacade = networkFacade
    val bus: RxBus = rxBus
    val errorParser: ExceptionParser = apiErrorParser
}