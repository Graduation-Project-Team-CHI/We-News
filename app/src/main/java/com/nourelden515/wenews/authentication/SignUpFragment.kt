package com.nourelden515.wenews.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nourelden515.wenews.MainActivity
import com.nourelden515.wenews.R
import com.nourelden515.wenews.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userRepository = UserRepository()
        viewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(userRepository)
        ).get(AuthViewModel::class.java)
        viewModel.checkLoggedIn()
        binding.btnSignup.setOnClickListener {
            viewModel.signUp(
                binding.yourEmail.editText?.text.toString(),
                binding.yourPassword.editText?.text.toString()

            )
        }
        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

}