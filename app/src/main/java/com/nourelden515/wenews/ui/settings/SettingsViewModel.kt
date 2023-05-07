package com.nourelden515.wenews.ui.settings

import androidx.lifecycle.ViewModel
import com.nourelden515.wenews.data.UserRepository

class SettingsViewModel(private val repository: UserRepository) : ViewModel() {
    fun logOut() {
        repository.logOut()
    }
}