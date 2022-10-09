package su.pank.filmapp.domain.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import org.koin.java.KoinJavaComponent.get
import su.pank.filmapp.source.remote.AuthApi

class LoginViewModel(authApi: AuthApi = get(AuthApi::class.java)) : ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    fun login() {
        val navController: NavHostController = get(NavHostController::class.java)
        navController.navigate("main") {
            popUpTo(0)
        }
    }
}