package com.nourelden515.wenews.data.remote

import com.nourelden515.wenews.data.remote.model.PredictionRequest
import com.nourelden515.wenews.data.remote.model.PredictionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PredictionApiService {
    @POST("predict")
    suspend fun getPrediction(@Body text: PredictionRequest): Response<PredictionResponse>
}