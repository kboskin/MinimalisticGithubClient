package com.github.minimalistic.data.common

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyInterceptor @Inject constructor()
: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(API_KEY, API_VALUE)

        return chain.proceed(builder.build())
    }

    companion object {
        private const val API_KEY = "apikey"
        private const val API_VALUE = "TODO//IMPLEMENT"
    }
}