package com.nourelden515.wenews.ui.explore

import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.remote.model.News
import com.nourelden515.wenews.ui.base.BaseAdapter
import com.nourelden515.wenews.ui.base.BaseInteractionListener

class ExploreAdapter(listener:ExploreInteractionListener): BaseAdapter<News>(listener) {
    override val layoutId: Int = R.layout.item_news
}

interface ExploreInteractionListener:BaseInteractionListener{
    fun onClickNews(item:News)
}