package su.pank.filmapp.domain

import su.pank.filmapp.R
import su.pank.filmapp.domain.model.Screen


class CONSTANTS {
    companion object {
        val BASE_URL = "http://cinema.areas.su/"

        val SCREENS = listOf(
            Screen("general", "Главная", R.drawable.tv),
            Screen("recommendation", "Подборка", R.drawable.rec),
            Screen("collection", "Коллекция", R.drawable.star),
            Screen("profile", "Профиль", R.drawable.profile)

        )

    }
}