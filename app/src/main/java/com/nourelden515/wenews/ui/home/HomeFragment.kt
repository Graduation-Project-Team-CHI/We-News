package com.nourelden515.wenews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nourelden515.wenews.ui.MainActivity
import com.nourelden515.wenews.R

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Show the custom ActionBar and BottomNavigation when the fragment is destroyed
        (activity as MainActivity).supportActionBar?.show()
        (activity as MainActivity).findViewById<View>(R.id.nav_view)?.visibility = View.VISIBLE

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}