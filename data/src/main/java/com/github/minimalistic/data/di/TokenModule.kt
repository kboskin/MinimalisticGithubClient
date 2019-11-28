package com.github.minimalistic.data.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import com.github.minimalistic.common.di.qualifires.Token
import com.github.minimalistic.data.core.network.OkHttpClientFactory
import com.github.minimalistic.data.core.qualifires.Interceptors
import com.github.minimalistic.data.core.qualifires.NetworkInterceptors
import javax.inject.Singleton

@Module
class TokenModule {

    @Token
    @Provides
    @Singleton
    fun tokenOkHttpClient(
        @Interceptors interceptors: List<Interceptor>,
        @NetworkInterceptors networkInterceptors: List<Interceptor>
    ): OkHttpClient = OkHttpClientFactory.create(interceptors, networkInterceptors)
}