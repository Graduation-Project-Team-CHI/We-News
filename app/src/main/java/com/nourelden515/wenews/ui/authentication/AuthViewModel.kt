package com.nourelden515.wenews.ui.authentication

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.common.api.ApiException
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
        try {

            if (email.isNotEmpty() && password.isNotEmpty()) {
                userRepository.signUp(email, password)
                    .addOnSuccessListener {
                        _isLoggedIn.value = userRepository.isLoggedIn()
                    }
            }
        }
            catch(e: ApiException) {
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

    fun authenticateWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                _isLoggedIn.value = task.isSuccessful
            }
    }
}





