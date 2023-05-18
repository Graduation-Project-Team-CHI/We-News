package com.nourelden515.wenews.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nourelden515.wenews.base.BaseViewModel
import com.nourelden515.wenews.data.remote.model.News
import com.nourelden515.wenews.data.remote.model.NewsResponse
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.utils.UiState
import kotlinx.coroutines.launch

class ExploreViewModel(private val repository: NewsRepository) : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName

    private val newsResponse = MutableLiveData<UiState<NewsResponse?>>()
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>>
        get() = _news

    fun getNewsByCategory(category: String) {
        viewModelScope.launch {
            repository.getNewsByCategory(category).collect {
                newsResponse.value = it
                if (newsResponse.value is UiState.Success) {
                    _news.postValue(
                        (newsResponse.value as UiState.Success<NewsResponse?>)
                            .data?.articles
                    )
                }
            }
        }
    }
}