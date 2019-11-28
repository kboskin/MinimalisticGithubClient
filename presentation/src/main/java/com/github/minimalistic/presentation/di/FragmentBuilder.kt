package com.github.minimalistic.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.github.minimalistic.common.di.scope.FragmentScope
import com.github.minimalistic.presentation.features.dispay_user_info.di.DisplayUserInfoModule
import com.github.minimalistic.presentation.features.dispay_user_info.ui.DisplayUserInfoFragment
import com.github.minimalistic.presentation.features.flow.ui.MainFlowFragment
import com.github.minimalistic.presentation.features.search.ui.SearchFragment

@Module
@Suppress("UnnecessaryAbstractClass", "TooManyFunctions")
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindMainFlowFragment(): MainFlowFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindSearchFragment(): SearchFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DisplayUserInfoModule::class])
    abstract fun bindDisplayUserInfoFragment(): DisplayUserInfoFragment
}