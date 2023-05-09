package com.nourelden515.wenews.data

import com.nourelden515.wenews.base.BaseRepository
import com.nourelden515.wenews.data.remote.API
import com.nourelden515.wenews.model.NewsRequest
import com.nourelden515.wenews.model.PredictionResponse
import com.nourelden515.wenews.utils.UiState
import kotlinx.coroutines.flow.Flow

class NewsRepository : BaseRepository() {
    private val api = API.apiService

    suspend fun predict(news: NewsRequest): Flow<UiState<PredictionResponse?>> {
        return wrapWithFlow { api.getPrediction(news) }
    }
}