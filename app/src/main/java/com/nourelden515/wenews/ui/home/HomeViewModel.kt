package com.nourelden515.wenews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nourelden515.wenews.base.BaseViewModel
import com.nourelden515.wenews.data.NewsRepository
import com.nourelden515.wenews.model.NewsRequest
import com.nourelden515.wenews.model.PredictionResponse
import com.nourelden515.wenews.utils.UiState
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NewsRepository) : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName

    val predictionResponse = MutableLiveData<UiState<PredictionResponse?>>()
    val news = MutableLiveData<String>()

    fun predict() {
        viewModelScope.launch {
            val news = NewsRequest(news.value ?: "Temp")
            repository.predict(news).collect {
                predictionResponse.postValue(it)
            }
        }
    }

}