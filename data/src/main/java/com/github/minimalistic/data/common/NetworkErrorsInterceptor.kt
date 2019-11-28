package com.github.minimalistic.data.common

import com.google.gson.Gson
import com.nullgr.core.hardware.NetworkChecker
import okhttp3.Interceptor
import okhttp3.Response
import com.github.minimalistic.common.errors.NetworkErrors
import java.net.HttpURLConnection.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkErrorsInterceptor @Inject constructor(
    private val networkChecker: NetworkChecker,
    private val gson: Gson
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (networkChecker.isInternetConnectionEnabled()) {
            val response = chain.proceed(chain.request())
            val responseCode = response.code()

            when {
                responseCode >= HTTP_INTERNAL_ERROR -> throw NetworkErrors.ServerError
                responseCode >= HTTP_NOT_FOUND -> throw NetworkErrors.NotFoundError
                else -> return response
            }
        } else {
            throw NetworkErrors.ConnectionError
        }
    }
}