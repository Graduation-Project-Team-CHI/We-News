package com.nourelden515.wenews.ui

import androidx.fragment.app.viewModels
import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.databinding.FragmentDetailsBinding
import com.nourelden515.wenews.ui.base.BaseFragment
import com.nourelden515.wenews.ui.base.ViewModelFactory

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_details
    override val viewModel: DetailsViewModel by viewModels()
}