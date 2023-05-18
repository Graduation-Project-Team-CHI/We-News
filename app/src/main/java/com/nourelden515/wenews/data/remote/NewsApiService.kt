package com.nourelden515.wenews.data.remote

import com.nourelden515.wenews.data.remote.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines?country=us")
    suspend fun getNewsByCategory(@Query("category") category: String): Response<NewsResponse>
}