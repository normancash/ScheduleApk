package com.uam.scheduleapk.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class ApiInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept:","application/json")
            .build()
        return chain.proceed(request)
    }
}

