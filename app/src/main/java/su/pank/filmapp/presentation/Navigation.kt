package su.pank.filmapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationSetUp(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { _ ->
            LogInScreen()
        }
        composable("reg") { RegScreen() }
        composable("main") { MainScreen()}
    }
}