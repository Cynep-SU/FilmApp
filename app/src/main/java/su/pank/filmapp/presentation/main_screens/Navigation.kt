package su.pank.filmapp.presentation.main_screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import su.pank.filmapp.domain.CONSTANTS.Companion.SCREENS
import su.pank.filmapp.domain.model.Film
import su.pank.filmapp.domain.viewmodel.FilmsViewModel

@Composable
fun SetUpNavigation(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = SCREENS.first().name,
        modifier = Modifier.padding(paddingValues)
    ) {
        for (el in SCREENS) {
            composable(el.name) { GeneralScreen(navHostController) }
        }
        composable(
            "film/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) {
            FilmScreen(
                it.arguments!!.getInt("filmId")
            )
        }

    }

}