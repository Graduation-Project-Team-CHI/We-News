package com.nourelden515.wenews.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nourelden515.wenews.data.UserRepository

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
}