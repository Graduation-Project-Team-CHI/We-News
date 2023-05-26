package com.nourelden515.wenews.ui.authentication

import android.content.ContentValues.TAG
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nourelden515.wenews.data.repository.UserRepository

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    // dialog.show()
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    fun signUp(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            userRepository.signUp(email, password)
                .addOnSuccessListener {
                    _isLoggedIn.value = userRepository.isLoggedIn()
                }
        } else {
            _errorMessage.value = "Please enter a valid email and password"
        }
    }

    fun login(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            userRepository.login(email, password)
                .addOnSuccessListener {
                    _isLoggedIn.value = userRepository.isLoggedIn()
                }
        } else {
            _errorMessage.value = "Please enter a valid email and password"
        }
    }

    fun checkLoggedIn() {
        _isLoggedIn.value = userRepository.isLoggedIn()
    }

    fun checkLoggedInUser(): Boolean {
        return userRepository.isLoggedIn()
    }

    fun authenticateWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                _isLoggedIn.value = task.isSuccessful
            }
    }

//    private val _loginResult = MutableLiveData<LoginResult>()
//    val loginResult: LiveData<LoginResult> = _loginResult

    private val firebaseAuth = FirebaseAuth.getInstance()

//    fun signInWithFacebook(accessToken: AccessToken) {
//
//        firebaseAuth.signInWithCredential(FacebookAuthProvider.getCredential(accessToken.token))
//            .addOnSuccessListener {
//                // Sign in succeeded
//                Log.d(TAG, "Facebook sign in succeeded!")
//            }
//            .addOnFailureListener {
//                // Sign in failed
//                Log.w(TAG, "Facebook sign in failed", it)
//            }
//    }

    private val TAG = "FacebookAuthViewModel"

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState>
        get() = _authState

    fun signUpWithFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.AUTHENTICATED
                } else {
                    Log.e(TAG, "signInWithCredential:failure", task.exception)
                    _authState.value = AuthState.UNAUTHENTICATED
                }
            }
    }

    fun loginWithFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.AUTHENTICATED
                } else {
                    Log.e(TAG, "signInWithCredential:failure", task.exception)
                    _authState.value = AuthState.UNAUTHENTICATED
                }
            }
    }

    enum class AuthState {
        AUTHENTICATED,
        UNAUTHENTICATED
    }
}