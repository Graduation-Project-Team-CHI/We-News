package com.nourelden515.wenews.ui.home

import androidx.fragment.app.viewModels
import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.databinding.FragmentHomeBinding
import com.nourelden515.wenews.ui.base.BaseFragment
import com.nourelden515.wenews.ui.base.ViewModelFactory
import com.nourelden515.wenews.utils.onClickBackFromNavigation

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels {
        ViewModelFactory(NewsRepository())
    }

    override fun setup() {
        onClickBackFromNavigation()
    }

}