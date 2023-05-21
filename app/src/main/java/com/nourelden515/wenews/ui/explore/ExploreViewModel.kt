package com.nourelden515.wenews.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nourelden515.wenews.data.remote.model.News
import com.nourelden515.wenews.data.remote.model.NewsResponse
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.ui.base.BaseViewModel
import com.nourelden515.wenews.utils.Constants
import com.nourelden515.wenews.utils.UiState
import kotlinx.coroutines.launch

class ExploreViewModel(private val repository: NewsRepository) : BaseViewModel(),ExploreInteractionListener {
    override val TAG: String = this::class.java.simpleName

    private val _newsResponse = MutableLiveData<UiState<NewsResponse>>()
    val newsResponse: LiveData<UiState<NewsResponse>>
        get() = _newsResponse

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>>
        get() = _news

    init {
        getGeneralNews()
    }

    private fun getNewsByCategory(category: String) {
        viewModelScope.launch {
            repository.getNewsByCategory(category).collect {
                _newsResponse.value = it
                if (_newsResponse.value is UiState.Success) {
                    _news.postValue(
                        (_newsResponse.value as UiState.Success<NewsResponse?>)
                            .data?.articles
                    )
                }
            }
        }
    }

    fun getGeneralNews(){
        resetNews()
        getNewsByCategory(Constants.Category.GENERAL.name)
    }
    fun getBusinessNews(){
        resetNews()
        getNewsByCategory(Constants.Category.BUSINESS.name)
    }
    fun getHealthNews(){
        resetNews()
        getNewsByCategory(Constants.Category.HEALTH.name)
    }
    fun getScienceNews(){
        resetNews()
        getNewsByCategory(Constants.Category.SCIENCE.name)
    }
    fun getSportsNews(){
        resetNews()
        getNewsByCategory(Constants.Category.SPORTS.name)
    }
    fun getTechnologyNews(){
        resetNews()
        getNewsByCategory(Constants.Category.TECHNOLOGY.name)
    }

    private fun resetNews(){
        _news.value = emptyList()
    }
    override fun onClickNews(item: News) {
        //
    }
}