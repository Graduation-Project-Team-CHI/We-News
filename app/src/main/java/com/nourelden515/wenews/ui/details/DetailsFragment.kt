package com.nourelden515.wenews.ui.details

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import com.nourelden515.wenews.R
import com.nourelden515.wenews.databinding.FragmentDetailsBinding
import com.nourelden515.wenews.ui.base.BaseFragment
import com.nourelden515.wenews.utils.EventObserve

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_details
    override val viewModel: DetailsViewModel by viewModels()

    override fun setup() {
        addObservers()
    }

    private fun addObservers() {
        viewModel.urlClick.observe(viewLifecycleOwner,
            EventObserve {
                goToUrl(it)
            }
        )
    }

    private fun goToUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}