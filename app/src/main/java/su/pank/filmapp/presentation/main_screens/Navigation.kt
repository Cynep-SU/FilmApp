package su.pank.filmapp.presentation.main_screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import su.pank.filmapp.domain.CONSTANTS.Companion.SCREENS

@Composable
fun SetUpNavigation(navHostController: NavHostController, paddingValues: PaddingValues){
    NavHost(
        navController = navHostController,
        startDestination = SCREENS.first().name,
        modifier = Modifier.padding(paddingValues)
    ){
        for (el in SCREENS){
            composable(el.name){ GeneralScreen() }
        }

    }

}