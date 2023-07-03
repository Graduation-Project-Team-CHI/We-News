package com.nourelden515.wenews.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nourelden515.wenews.data.repository.UserRepository
import com.nourelden515.wenews.ui.base.BaseViewModel

class AuthViewModel(private val userRepository: UserRepository) : BaseViewModel() {
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
                }.addOnFailureListener {
                    log(it.message.toString())
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

    fun authenticateWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                _isLoggedIn.value = task.isSuccessful
            }.addOnFailureListener {
                log(it.message.toString())
            }
    }

    override val TAG: String = this::class.java.simpleName
}





