package com.nourelden515.wenews.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.nourelden515.wenews.R
import com.nourelden515.wenews.ui.MainActivity

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    abstract val TAG: String
    abstract val layoutIdFragment: Int
    abstract val viewModel: ViewModel
    private lateinit var _binding: VB
    protected val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            layoutIdFragment,
            container, false
        )

        _binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    protected open fun setup() {}

    protected fun log(value: Any) {
        Log.e(TAG, value.toString())
    }

    fun hideActionBarAndBottomNav() {
        // Hide the custom ActionBar
        (activity as MainActivity).supportActionBar?.hide()

        // Hide the BottomNavigation
        (activity as MainActivity).findViewById<View>(R.id.nav_view)?.visibility = View.GONE
    }

    fun showActionBarAndBottomNav() {
        // Show the custom ActionBar and BottomNavigation when the fragment is destroyed
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity).findViewById<View>(R.id.nav_view)?.visibility = View.VISIBLE
    }
}