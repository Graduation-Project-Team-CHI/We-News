package com.nourelden515.wenews.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nourelden515.wenews.authentication.AuthViewModel
import com.nourelden515.wenews.data.UserRepository
import com.nourelden515.wenews.ui.settings.SettingsViewModel

class AuthViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(userRepository) as T

            modelClass.isAssignableFrom(SettingsViewModel::class.java) ->
                SettingsViewModel(userRepository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}
