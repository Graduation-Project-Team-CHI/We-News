package com.nourelden515.wenews.data.remote

import com.nourelden515.wenews.model.NewsRequest
import com.nourelden515.wenews.model.PredictionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("predict")
    suspend fun getPrediction(@Body text: NewsRequest): Response<PredictionResponse>
}