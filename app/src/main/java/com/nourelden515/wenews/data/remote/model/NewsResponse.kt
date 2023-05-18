package com.nourelden515.wenews.data.remote.model

import com.squareup.moshi.Json

data class NewsResponse(
    @Json(name = "status") val status: String,
    @Json(name = "totalResults") val totalResults: String,
    @Json(name = "articles") val articles: List<News>
)
