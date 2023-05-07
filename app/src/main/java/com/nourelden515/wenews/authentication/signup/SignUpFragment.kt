package com.nourelden515.wenews.authentication.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nourelden515.wenews.ui.MainActivity
import com.nourelden515.wenews.R
import com.nourelden515.wenews.authentication.AuthViewModel
import com.nourelden515.wenews.base.AuthViewModelFactory
import com.nourelden515.wenews.data.UserRepository
import com.nourelden515.wenews.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            AuthViewModelFactory(UserRepository())
        )[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Hide the custom ActionBar
        (activity as MainActivity).supportActionBar?.hide()

        // Hide the BottomNavigation
        (activity as MainActivity).findViewById<View>(R.id.nav_view)?.visibility = View.GONE

        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkLoggedIn()
        binding.btnSignup.setOnClickListener {
            viewModel.signUp(
                binding.yourEmail.editText?.text.toString(),
                binding.yourPassword.editText?.text.toString()

            )
        }
        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
            }
        }
        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}