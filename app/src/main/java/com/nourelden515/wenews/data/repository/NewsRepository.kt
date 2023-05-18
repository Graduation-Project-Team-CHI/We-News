package com.nourelden515.wenews.data.repository

import com.nourelden515.wenews.base.BaseRepository
import com.nourelden515.wenews.data.remote.API
import com.nourelden515.wenews.data.remote.model.NewsResponse
import com.nourelden515.wenews.data.remote.model.PredictionRequest
import com.nourelden515.wenews.data.remote.model.PredictionResponse
import com.nourelden515.wenews.utils.UiState
import kotlinx.coroutines.flow.Flow

class NewsRepository : BaseRepository() {
    private val predictionApi = API.predictionApiService
    private val newsApi = API.newsApiService

    suspend fun predict(news: PredictionRequest): Flow<UiState<PredictionResponse?>> {
        return wrapWithFlow { predictionApi.getPrediction(news) }
    }

    suspend fun getNewsByCategory(category: String): Flow<UiState<NewsResponse?>> {
        return wrapWithFlow { newsApi.getNewsByCategory(category) }
    }
}