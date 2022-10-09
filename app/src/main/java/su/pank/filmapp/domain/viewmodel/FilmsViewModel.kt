package su.pank.filmapp.domain.viewmodel

import androidx.lifecycle.ViewModel
import su.pank.filmapp.R
import su.pank.filmapp.domain.model.Film

class FilmsViewModel: ViewModel() {
    val recommendFilm = Film("test", R.drawable.test_logo)
    val trendingFilms = listOf(recommendFilm, recommendFilm, recommendFilm, recommendFilm, recommendFilm)

}