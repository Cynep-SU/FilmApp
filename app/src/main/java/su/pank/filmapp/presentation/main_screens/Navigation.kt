package su.pank.filmapp.presentation.main_screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import su.pank.filmapp.domain.CONSTANTS.Companion.SCREENS
import su.pank.filmapp.domain.model.Film

class Token : TypeToken<List<String>>()


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
            "film/{id}/{name}/{logo}/{tags}/{description}/{ageRate}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType },
                navArgument("logo") { type = NavType.StringType },
                navArgument("tags") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("ageRate") { type = NavType.StringType },
                )
        ) {
            val args = it.arguments!!
            val film = Film(args.getInt("id"), args.getString("name")!!, args.getString("logo")!!.replace(" ", "/"), Gson().fromJson(args.getString("tags"), Token().type), args.getString("description")!!)


            FilmScreen(
                film, navHostController
            )
        }

    }

}