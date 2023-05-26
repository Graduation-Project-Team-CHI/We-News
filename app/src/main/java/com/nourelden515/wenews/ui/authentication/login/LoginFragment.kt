package com.nourelden515.wenews.ui.authentication.login

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
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

    val callbackManager = CallbackManager.Factory.create()
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
        /*
        viewModel.authState.observe(viewLifecycleOwner) { authState ->
            when (authState) {
                FacebookAuthViewModel.AuthState.AUTHENTICATED -> {
                    // User is authenticated, navigate to next screen
                }
                FacebookAuthViewModel.AuthState.UNAUTHENTICATED -> {
                    // Authentication failed, show error message
                }
            }
        * */
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
        binding.facebookIcon.setOnClickListener{
            loginWithFacebook()
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
    private fun loginWithFacebook() {
        // Login with Facebook using LoginManager
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    TODO("Not yet implemented")
                }

                override fun onError(error: FacebookException) {
                    TODO("Not yet implemented")
                }
                override fun onSuccess(result: LoginResult) {
                    result.let {
                        // Get Access token from result and pass it to view model to sign in with Firebase
                        val accessToken = result.accessToken
                        viewModel.signUpWithFacebook(accessToken)
                    }
                }
            })
    }
}
