package com.nourelden515.wenews.ui.authentication.login

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.repository.UserRepository
import com.nourelden515.wenews.databinding.FragmentLoginBinding
import com.nourelden515.wenews.ui.authentication.AuthViewModel
import com.nourelden515.wenews.ui.base.BaseFragment
import com.nourelden515.wenews.ui.base.ViewModelFactory

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel: AuthViewModel by viewModels {
        ViewModelFactory(UserRepository())
    }

    override fun setup() {

        viewModel.checkLoggedIn()
        binding.btnLogin.setOnClickListener {
            log("login")
            viewModel.login(
                binding.etEmail.editText?.text.toString(),
                binding.etPassword.editText?.text.toString()
            )
        }

        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                log(isLoggedIn)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
        binding.textView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
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
        GoogleSignIn.getSignedInAccountFromIntent(result.data).addOnSuccessListener {
            viewModel.authenticateWithGoogle(it.idToken!!)
        }.addOnFailureListener {
            Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_LONG).show()
            log(it.message.toString())
        }
    }

}






