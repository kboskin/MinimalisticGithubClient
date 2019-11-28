package com.github.minimalistic.presentation.core.pm

import com.nullgr.core.adapter.items.ListItem
import com.nullgr.core.rx.bindEmpty

abstract class BaseListPm(
    services: ServiceFacade
) : BasePm(services) {

    override fun onCreate() {
        super.onCreate()

        items.observable
            .bindEmpty(emptyControl.visibilityState.consumer)
            .subscribe()
            .untilDestroy()
    }

    override val isEmptyScreen: Boolean
        get() = !items.hasValue()

    val items = State<List<ListItem>>()
}