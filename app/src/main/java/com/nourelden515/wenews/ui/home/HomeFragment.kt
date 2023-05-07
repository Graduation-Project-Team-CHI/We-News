package com.nourelden515.wenews.ui.home

import androidx.lifecycle.ViewModelProvider
import com.nourelden515.wenews.R
import com.nourelden515.wenews.base.BaseFragment
import com.nourelden515.wenews.base.ViewModelFactory
import com.nourelden515.wenews.data.NewsRepository
import com.nourelden515.wenews.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(NewsRepository())
        )[HomeViewModel::class.java]
    }

    override fun setup() {
        showActionBarAndBottomNav()

    }

}