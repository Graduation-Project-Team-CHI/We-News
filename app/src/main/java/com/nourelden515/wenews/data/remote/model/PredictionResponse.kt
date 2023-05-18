package com.nourelden515.wenews.data.remote.model

import com.squareup.moshi.Json

data class PredictionResponse(
    @Json(name = "prediction") val prediction: String,
)