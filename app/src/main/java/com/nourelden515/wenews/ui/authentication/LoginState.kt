package com.nourelden515.wenews.ui.authentication

import com.facebook.login.LoginResult

sealed class LoginState {

    object Loading : LoginState()
    data class Success(val result: LoginResult) : LoginState()
    data class Error(val message: String?) : LoginState()
    object Cancelled : LoginState()
}