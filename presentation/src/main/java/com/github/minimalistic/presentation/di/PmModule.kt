package com.github.minimalistic.presentation.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.dmdev.rxpm.PresentationModel
import com.github.minimalistic.presentation.core.pm.PmKey
import com.github.minimalistic.presentation.core.pm.factory.GeneralPmFactory
import com.github.minimalistic.presentation.core.pm.factory.PmFactory
import com.github.minimalistic.presentation.features.app.pm.AppPm
import com.github.minimalistic.presentation.features.dispay_user_info.pm.DisplayUserInfoPm
import com.github.minimalistic.presentation.features.flow.pm.MainFlowPm
import com.github.minimalistic.presentation.features.search.pm.SearchPm

@Suppress("TooManyFunctions")
@Module
abstract class PmModule {

    @Binds
    abstract fun viewModelFactory(factory: GeneralPmFactory): PmFactory

    @Binds
    @IntoMap
    @PmKey(AppPm::class)
    abstract fun bindAppPm(pm: AppPm): PresentationModel

    @Binds
    @IntoMap
    @PmKey(SearchPm::class)
    abstract fun bindSearchPm(pm: SearchPm): PresentationModel

    @Binds
    @IntoMap
    @PmKey(MainFlowPm::class)
    abstract fun bindMainFlowPm(pm: MainFlowPm): PresentationModel

    @Binds
    @IntoMap
    @PmKey(DisplayUserInfoPm::class)
    abstract fun bindDisplayUserInfoPm(pm: DisplayUserInfoPm): PresentationModel

}