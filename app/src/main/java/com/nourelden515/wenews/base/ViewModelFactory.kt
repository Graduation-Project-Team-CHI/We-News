package com.nourelden515.wenews.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nourelden515.wenews.data.repository.NewsRepository
import com.nourelden515.wenews.data.repository.UserRepository
import com.nourelden515.wenews.ui.authentication.AuthViewModel
import com.nourelden515.wenews.ui.explore.ExploreViewModel
import com.nourelden515.wenews.ui.home.HomeViewModel
import com.nourelden515.wenews.ui.settings.SettingsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(repository as UserRepository) as T

            modelClass.isAssignableFrom(SettingsViewModel::class.java) ->
                SettingsViewModel(repository as UserRepository) as T

            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(repository as NewsRepository) as T

            modelClass.isAssignableFrom(ExploreViewModel::class.java) ->
                ExploreViewModel(repository as NewsRepository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}
