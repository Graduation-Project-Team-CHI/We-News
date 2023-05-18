package com.nourelden515.wenews.ui.home.check

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nourelden515.wenews.R
import com.nourelden515.wenews.base.BaseFragment
import com.nourelden515.wenews.base.ViewModelFactory
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.databinding.FragmentCheckBinding
import com.nourelden515.wenews.ui.home.HomeViewModel

class CheckFragment : BaseFragment<FragmentCheckBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_check
    override val viewModel: HomeViewModel by activityViewModels {
        ViewModelFactory(NewsRepository())
    }
    private var language = Language.ENGLISH

    override fun setup() {

        binding.segmentedButtonGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.button_english -> {
                    language = Language.ENGLISH
                }

                R.id.button_arabic -> {
                    language = Language.ARABIC
                }
            }
        }

        binding.btnCheckNow.setOnClickListener {
            when (language) {
                Language.ENGLISH -> {
                    findNavController().navigate(R.id.action_checkFragment_to_resultFragment)
                }

                Language.ARABIC -> {
                    //to do
                }
            }
        }
    }

    enum class Language {
        ENGLISH, ARABIC
    }
}