package com.github.minimalistic.data.di

import dagger.Module
import dagger.Provides
import com.github.minimalistic.data.core.qualifires.ServerUrl

@Module
@Suppress("FunctionOnlyReturningConstant")
class ApiConstantsModule {

    @Provides
    @ServerUrl
    fun provideServerUrl() = SERVER_URL_PROD

    private companion object {
        const val SERVER_URL_PROD = "https://api.github.com/"
    }
}