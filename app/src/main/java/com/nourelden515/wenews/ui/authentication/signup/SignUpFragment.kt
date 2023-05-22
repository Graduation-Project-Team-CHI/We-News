package com.nourelden515.wenews.ui.authentication.signup

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.repository.UserRepository
import com.nourelden515.wenews.databinding.FragmentSignUpBinding
import com.nourelden515.wenews.ui.authentication.AuthViewModel
import com.nourelden515.wenews.ui.base.BaseFragment
import com.nourelden515.wenews.ui.base.ViewModelFactory

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_sign_up
    override val viewModel: AuthViewModel by viewModels {
        ViewModelFactory(UserRepository())
    }


    override fun setup() {
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