package com.nourelden515.wenews.ui.home.result

import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nourelden515.wenews.R
import com.nourelden515.wenews.base.BaseFragment
import com.nourelden515.wenews.base.ViewModelFactory
import com.nourelden515.wenews.data.NewsRepository
import com.nourelden515.wenews.databinding.FragmentResultBinding
import com.nourelden515.wenews.ui.home.HomeViewModel
import org.eazegraph.lib.models.PieModel

class ResultFragment : BaseFragment<FragmentResultBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment: Int = R.layout.fragment_result
    override val viewModel: HomeViewModel by activityViewModels {
        ViewModelFactory(NewsRepository())
    }

    override fun setup() {

        viewModel.predict()

        binding.btnCheckNew.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.predictionResponse.observe(viewLifecycleOwner) {
            it.toData()?.apply {
                val prediction = this.prediction.toFloat() * 100
                setUpPieChart(prediction, 100 - prediction)
            }
        }
    }

    private fun setUpPieChart(real: Float, fake: Float) {
        binding.pieChart.addPieSlice(
            PieModel(
                fake,
                ContextCompat.getColor(requireContext(), R.color.red)
            )
        )
        binding.pieChart.addPieSlice(
            PieModel(
                real,
                ContextCompat.getColor(requireContext(), R.color.green)
            )
        )

    }
}
