package com.nourelden515.wenews.settings

import androidx.lifecycle.ViewModel
import com.nourelden515.wenews.authentication.UserRepository

class SettingsViewModel(private val repository: UserRepository) : ViewModel() {
    fun logOut() {
        repository.logOut()
    }
}