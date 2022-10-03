package su.pank.filmapp.domain.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import su.pank.filmapp.source.remote.AuthApi

class LoginViewModel(authApi: AuthApi, navHostController: NavHostController): ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    fun login(){
        TODO()
    }
}