package su.pank.filmapp.domain

import androidx.compose.ui.graphics.Color
import su.pank.filmapp.R
import su.pank.filmapp.domain.model.Screen


class CONSTANTS {
    companion object {
        val AUTH_URL = "http://cinema.areas.su/"
        val BD_URL = "https://www.breakingbadapi.com/api/"
        val SOUL_GOODMAN = "https://github.com/Cynep-SU/FilmApp/blob/master/videos/saul.mp4?raw=true"

        val SCREENS = listOf(
            Screen("general", "Главная", R.drawable.tv),
            Screen("recommendation", "Подборка", R.drawable.rec),
            Screen("collection", "Коллекция", R.drawable.star),
            Screen("profile", "Профиль", R.drawable.profile)
        )

        val ageRates =
            mapOf(
                "18+" to Color(0xFFEF3A01),
                "16+" to Color(0xFFF26E45),
                "12+" to Color(0xFFF4A992),
                "6+" to Color(0xFFFAD5C9),
                "0" to Color.White
                )

    }
}