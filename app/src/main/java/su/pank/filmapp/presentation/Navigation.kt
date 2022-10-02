package su.pank.filmapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.getKoin
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

@Composable
fun NavigationSetUp(navController: NavHostController) {
    NavHost(navController,startDestination = "login") {
        composable("login") { LogInScreen(navController) }
        composable("reg") { LogInScreen(navController) }
    }
}