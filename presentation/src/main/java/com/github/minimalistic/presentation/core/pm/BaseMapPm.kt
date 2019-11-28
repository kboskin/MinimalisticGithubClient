package com.github.minimalistic.presentation.core.pm

import me.dmdev.rxpm.map.MapPmExtension

abstract class BaseMapPm(
    services: ServiceFacade
) : BasePm(services), MapPmExtension {

    override val mapReadyState = MapPmExtension.MapReadyState()
}