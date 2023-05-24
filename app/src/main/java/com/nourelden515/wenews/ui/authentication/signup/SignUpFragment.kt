package com.nourelden515.wenews.ui.authentication.signup

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
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
        binding.googleIcon.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleSignInIntent = GoogleSignIn.getClient(requireActivity(), gso).signInIntent
            googleSignInLauncher.launch(googleSignInIntent)
        }
    }
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            viewModel.authenticateWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Toast.makeText(requireContext(), "Not successful ", Toast.LENGTH_LONG).show()
        }
    }
}
