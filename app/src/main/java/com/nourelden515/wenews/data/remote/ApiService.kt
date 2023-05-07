package com.nourelden515.wenews.data.remote

import com.nourelden515.wenews.model.PredictionResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("predict")
    suspend fun getPrediction(@Path("text") news: String): Response<PredictionResponse>
}