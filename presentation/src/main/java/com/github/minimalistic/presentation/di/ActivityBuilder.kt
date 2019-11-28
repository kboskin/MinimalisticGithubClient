package com.github.minimalistic.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.github.minimalistic.common.di.scope.ActivityScope
import com.github.minimalistic.presentation.features.app.ui.AppActivity

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindAppActivity(): AppActivity
}