package su.pank.filmapp.domain.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import su.pank.filmapp.domain.model.LogIn
import su.pank.filmapp.source.remote.AuthApi

class LoginViewModel(authApi: AuthApi): ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    fun login(){
        TODO()
    }
}