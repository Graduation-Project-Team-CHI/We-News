package com.nourelden515.wenews.data.remote

import com.nourelden515.wenews.BuildConfig
import com.nourelden515.wenews.utils.Constants.NEWS_BASE_URL
import com.nourelden515.wenews.utils.Constants.PREDICTION_BASE_URL
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

    //Begin prediction Region

    private val headerInterceptor = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder().also {
            it.addHeader("Content-Type", "application/json")
        }.build())
    }

    private val predictionClient = OkHttpClient.Builder().apply {
        addInterceptor(headerInterceptor)
        addInterceptor(logInterceptor)
    }.build()

    private val predictionRetrofit = Retrofit.Builder()
        .baseUrl(PREDICTION_BASE_URL)
        .client(predictionClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val predictionApiService: PredictionApiService =
        predictionRetrofit.create(PredictionApiService::class.java)

    //End prediction Region

    //Begin news Region
    private val newsHeaderInterceptor = Interceptor { chain ->
        val url = chain.request().url.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()
        chain.proceed(chain.request().newBuilder().url(url).build())
    }

    private val newsClient = OkHttpClient.Builder().apply {
        addInterceptor(newsHeaderInterceptor)
        addInterceptor(logInterceptor)
    }.build()

    private val newsRetrofit = Retrofit.Builder()
        .baseUrl(NEWS_BASE_URL)
        .client(newsClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val newsApiService: NewsApiService =
        newsRetrofit.create(NewsApiService::class.java)

    //End news Region
}