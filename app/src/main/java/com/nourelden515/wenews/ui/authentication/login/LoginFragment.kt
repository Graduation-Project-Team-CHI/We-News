package com.nourelden515.wenews.ui.authentication.login

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.nourelden515.wenews.R
import com.nourelden515.wenews.data.repository.UserRepository
import com.nourelden515.wenews.databinding.FragmentLoginBinding
import com.nourelden515.wenews.ui.authentication.AuthViewModel
import com.nourelden515.wenews.ui.base.BaseFragment
import com.nourelden515.wenews.ui.base.ViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val TAG: String = this::class.java.simpleName
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel: AuthViewModel by viewModels {
        ViewModelFactory(UserRepository())
    }

    override fun setup() {

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
        binding.googleAuth.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleSignInIntent = GoogleSignIn.getClient(requireActivity(), gso).signInIntent
            googleSignInLauncher.launch(googleSignInIntent)
            loding()
        }
    }
    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            viewModel.authenticateWithGoogle(account.idToken!!)
            Toast.makeText(requireContext(), "successful ", Toast.LENGTH_LONG).show()
        } catch (e: ApiException) {
            Toast.makeText(requireContext(), "Not successful ", Toast.LENGTH_LONG).show()
            val errorMessage = task.exception?.message
            Log.e("Login", errorMessage.toString())
            Toast.makeText(context,  "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    private  fun loding(){
        binding.loading.visibility = View.VISIBLE
        binding.loading.progress = 0
        lifecycleScope.launch {
            repeat(10) { progress ->
                delay(500)
                binding.loading.progress = (progress + 1) * 10
            }
            binding.loading.visibility = View.GONE
        }
    }
}






