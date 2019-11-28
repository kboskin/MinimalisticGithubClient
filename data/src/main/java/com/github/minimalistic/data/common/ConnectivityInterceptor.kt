package com.github.minimalistic.data.common

import com.nullgr.core.hardware.NetworkChecker
import okhttp3.Interceptor
import okhttp3.Response
import com.github.minimalistic.common.errors.NetworkErrors
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(
    private val checker: NetworkChecker
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        if (checker.isInternetConnectionEnabled()) {
            chain.proceed(chain.request())
        } else {
            throw NetworkErrors.ConnectionError
        }
}
