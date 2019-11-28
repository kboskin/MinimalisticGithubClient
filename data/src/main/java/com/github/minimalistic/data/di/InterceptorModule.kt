package com.github.minimalistic.data.di

import com.github.minimalistic.data.core.qualifires.NetworkInterceptors
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import com.github.minimalistic.data.common.ConnectivityInterceptor
import com.github.minimalistic.data.core.network.TimberInterceptorLogger
import com.github.minimalistic.data.core.qualifires.Interceptors
import com.github.minimalistic.data.common.NetworkErrorsInterceptor
import javax.inject.Singleton

@Module
class InterceptorModule(
    private val tag: String,
    private val level: HttpLoggingInterceptor.Level
) {

    @Provides
    @Singleton
    fun logger(): HttpLoggingInterceptor.Logger = TimberInterceptorLogger(tag)

    @Provides
    @Singleton
    fun httpLoggingInterceptor(logger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor =
        HttpLoggingInterceptor(logger).setLevel(level)

    @Provides
    @Singleton
    @Interceptors
    fun interceptors(
        connectivityInterceptor: ConnectivityInterceptor,
        networkErrorsInterceptor: NetworkErrorsInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): List<@JvmWildcard Interceptor> =
        listOf(
            httpLoggingInterceptor,
            connectivityInterceptor,
            networkErrorsInterceptor
        )

    @Provides
    @Singleton
    @NetworkInterceptors
    fun networkInterceptors(): List<@JvmWildcard Interceptor> = emptyList()
}