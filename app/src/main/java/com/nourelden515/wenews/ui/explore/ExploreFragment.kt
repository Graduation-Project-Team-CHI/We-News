package com.nourelden515.wenews.ui.explore

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.databinding.FragmentExploreBinding
import com.nourelden515.wenews.ui.base.BaseFragment
import com.nourelden515.wenews.ui.base.ViewModelFactory
import com.nourelden515.wenews.utils.EventObserve

class ExploreFragment : BaseFragment<FragmentExploreBinding>(), TabLayout.OnTabSelectedListener {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_explore
    override val viewModel: ExploreViewModel by viewModels {
        ViewModelFactory(NewsRepository())
    }

    override fun setup() {
        initiateAdapter()
        addTabs()
        binding.tabsLayout.addOnTabSelectedListener(this)
        addObservers()
    }

    private fun addObservers() {
        viewModel.newsClick.observe(viewLifecycleOwner,
            EventObserve {
                val action = ExploreFragmentDirections.actionExploreFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }
        )
    }

    private fun addTabs() {
        with(binding.tabsLayout) {
            addTab(newTab().setText(getString(R.string.general)))
            addTab(newTab().setText(getString(R.string.business)))
            addTab(newTab().setText(getString(R.string.health)))
            addTab(newTab().setText(getString(R.string.science)))
            addTab(newTab().setText(getString(R.string.sports)))
            addTab(newTab().setText(getString(R.string.technology)))
        }
    }

    /*private fun addTabs() {
        val tabLayout = binding.tabsLayout

        val tabTitles = listOf(
            getString(R.string.general),
            getString(R.string.business),
            getString(R.string.health),
            getString(R.string.science),
            getString(R.string.sports),
            getString(R.string.technology)
        )

        tabTitles.forEach { title ->
            val tab = tabLayout.newTab()
            val tabView = layoutInflater.inflate(R.layout.item_custom_tab, null)
            val tabTextView = tabView.findViewById<TextView>(android.R.id.text1)
            tabTextView.text = title
            tab.customView = tabView
            tabLayout.addTab(tab)
        }
    }*/

    private fun initiateAdapter() {
        val adapter = ExploreAdapter(viewModel)
        binding.recycleViewExplore.adapter = adapter
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        with(viewModel) {
            when (tab?.text) {
                getString(R.string.general) -> getGeneralNews()

                getString(R.string.business) -> getBusinessNews()

                getString(R.string.health) -> getHealthNews()

                getString(R.string.science) -> getScienceNews()

                getString(R.string.sports) -> getSportsNews()

                getString(R.string.technology) -> getTechnologyNews()
            }
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onResume() {
        super.onResume()
        restoreTabLayoutState()
        restoreRecycleViewState()
    }

    private fun restoreRecycleViewState() {
        if (viewModel.stateInitialized()) {
            binding.recycleViewExplore.layoutManager?.onRestoreInstanceState(
                viewModel.restoreRecyclerViewState()
            )
        }
    }

    private fun restoreTabLayoutState() {
        if (viewModel.tabLayoutStateInitialized()) {
            with(binding.tabsLayout) {
                val currentTab = viewModel.restoreTabLayoutState()
                setScrollPosition(currentTab, 0f, true)
                getTabAt(currentTab)?.select()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveTabLayoutState(binding.tabsLayout.selectedTabPosition)
        binding.recycleViewExplore.layoutManager?.onSaveInstanceState()
            ?.let { viewModel.saveRecyclerViewState(it) }
    }
}