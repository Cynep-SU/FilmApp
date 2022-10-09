package su.pank.filmapp.presentation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import su.pank.filmapp.domain.CONSTANTS.Companion.SCREENS
import su.pank.filmapp.presentation.main_screens.SetUpNavigation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController: NavHostController = rememberNavController()

    Scaffold(bottomBar = {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            SCREENS.forEach { screen ->
                NavigationBarItem(
                    icon =
                    {
                        Icon(
                            painter = painterResource(
                                id = screen.icon
                            ),
                            contentDescription = null
                        )
                    },
                    label = { Text(screen.label) },
                    selected = currentDestination?.hierarchy?.any {
                        screen.name in (it.route ?: "")
                    } == true,
                    onClick = { navController.navigate(screen.name) }
                )
            }
        }
    }) {
        SetUpNavigation(navHostController = navController, paddingValues = it)
    }
}