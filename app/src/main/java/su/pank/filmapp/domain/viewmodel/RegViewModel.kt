package su.pank.filmapp.domain.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.koin.java.KoinJavaComponent.get
import su.pank.filmapp.source.remote.AuthApi

class RegViewModel(authApi: AuthApi = get(AuthApi::class.java)) : ViewModel() {
    var name = mutableStateOf("")
    var lastName = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var secondPassword = mutableStateOf("")

    fun reg() {
        TODO()
    }
}