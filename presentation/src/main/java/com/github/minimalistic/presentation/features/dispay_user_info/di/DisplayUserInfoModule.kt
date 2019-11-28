package com.github.minimalistic.presentation.features.dispay_user_info.di

import androidx.recyclerview.widget.RecyclerView
import com.nullgr.core.adapter.AdapterDelegatesFactory
import com.nullgr.core.adapter.DynamicAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.github.minimalistic.common.di.scope.FragmentScope
import com.github.minimalistic.presentation.features.dispay_user_info.ui.DisplayUserInfoDelegatesFactory

@Module(includes = [DisplayUserInfoModule.Declarations::class])
class DisplayUserInfoModule {

    @Module
    interface Declarations {
        @Binds
        @FragmentScope
        fun delegatesFactory(factory: DisplayUserInfoDelegatesFactory): AdapterDelegatesFactory
    }

    @Provides
    fun dynamicAdapter(factory: AdapterDelegatesFactory): DynamicAdapter = DynamicAdapter(factory)

    @Provides
    @FragmentScope
    fun viewPool(): RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
}