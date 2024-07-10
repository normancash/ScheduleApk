package com.uam.scheduleapk.remote

import com.uam.scheduleapk.util.Global
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {

    //val okHttpClient = OkHttpClient.Builder().addInterceptor(ApiInterceptor()).build()

    val okHttpClient = OkHttpClient.Builder().build()



    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Global.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}