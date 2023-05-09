package com.nourelden515.wenews.data.remote

import com.nourelden515.wenews.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object API {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val headerInterceptor = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder().also {
            it.addHeader("Content-Type", "application/json")
        }.build())
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(headerInterceptor)
        addInterceptor(logInterceptor)
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    val apiService: ApiService = retrofit.create(ApiService::class.java)

}