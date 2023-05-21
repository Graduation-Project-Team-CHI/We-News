package com.nourelden515.wenews.ui.authentication.login

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nourelden515.wenews.R
import com.nourelden515.wenews.ui.base.ViewModelFactory
import com.nourelden515.wenews.ui.base.BaseFragment
import com.nourelden515.wenews.data.repository.UserRepository
import com.nourelden515.wenews.databinding.FragmentLoginBinding
import com.nourelden515.wenews.ui.authentication.AuthViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel: AuthViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(UserRepository())
        )[AuthViewModel::class.java]
    }

    override fun setup() {

        hideActionBarAndBottomNav()

        viewModel.checkLoggedIn()
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.etEmail.editText?.text.toString(),
                binding.etPassword.editText?.text.toString()
            )
        }

        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}
