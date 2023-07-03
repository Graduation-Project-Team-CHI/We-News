package com.nourelden515.wenews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nourelden515.wenews.ui.base.BaseViewModel
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.data.remote.model.PredictionRequest
import com.nourelden515.wenews.data.remote.model.PredictionResponse
import com.nourelden515.wenews.utils.UiState
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NewsRepository) : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName

    val predictionResponse = MutableLiveData<UiState<PredictionResponse?>>()
    val news = MutableLiveData<String>()

    fun predict() {
        viewModelScope.launch {
            val news = PredictionRequest(news.value ?: "")
            repository.predict(news).collect {
                predictionResponse.postValue(it)
            }
        }
    }

}