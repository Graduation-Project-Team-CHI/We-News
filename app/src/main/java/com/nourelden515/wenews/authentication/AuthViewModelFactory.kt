package com.nourelden515.wenews.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(userRepository) as T
    }
}
