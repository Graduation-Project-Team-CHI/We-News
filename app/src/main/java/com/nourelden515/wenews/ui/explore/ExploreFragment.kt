package com.nourelden515.wenews.ui.explore

import androidx.lifecycle.ViewModelProvider
import com.nourelden515.wenews.R
import com.nourelden515.wenews.base.BaseFragment
import com.nourelden515.wenews.base.ViewModelFactory
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.databinding.FragmentExploreBinding

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_explore
    override val viewModel: ExploreViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(NewsRepository())
        )[ExploreViewModel::class.java]
    }

    override fun setup() {
        //viewModel.getNewsByCategory("health")
        viewModel.news.observe(viewLifecycleOwner){
            log(it)
        }
    }
}