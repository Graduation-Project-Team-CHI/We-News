package com.nourelden515.wenews.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.nourelden515.wenews.data.remote.model.News
import com.nourelden515.wenews.ui.base.BaseViewModel
import com.nourelden515.wenews.utils.EventHandler

class DetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    override val TAG: String = this::class.java.simpleName

    private val newsArgs = DetailsFragmentArgs.fromSavedStateHandle(state)

    private val _urlClick = MutableLiveData<EventHandler<String>>()
    val urlClick: LiveData<EventHandler<String>>
        get() = _urlClick

    private val _news = MutableLiveData<News>()
    val news: LiveData<News>
        get() = _news

    init {
        _news.postValue(newsArgs.news)
    }

    fun onClickUrl(){
        _urlClick.postValue(EventHandler(news.value?.url.toString()))
    }
}