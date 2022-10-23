package su.pank.filmapp.domain.viewmodel

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.get
import su.pank.filmapp.domain.model.Film
import su.pank.filmapp.source.remote.BreakingBadApi

enum class Status {
    Loaded,
    Success
}

class FilmsViewModel : ViewModel() {
    var status by mutableStateOf(Status.Loaded)
    val scrollState = ScrollState(0)
    lateinit var recommendFilm: Film
    lateinit var trendingFilms: List<Film>
    lateinit var newFilms: List<Film>
    lateinit var forYou: List<Film>
    val watched = "https://github.com/Cynep-SU/FilmApp/blob/master/videos/saul.mp4?raw=true"
    private lateinit var films: List<Film>

    init {
        val filmApi: BreakingBadApi = get(BreakingBadApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            awaitAll(
                async {
                    recommendFilm = filmApi.getRandomCharacter(1)[0]
                },
                async {
                    forYou = filmApi.getRandomCharacter(10)
                },
                async {
                    newFilms = filmApi.getRandomCharacter(10)
                }, async {
                    films = filmApi.getAllCharacters()
                })
            trendingFilms = films
            status = Status.Success
        }
    }

    fun openFilm(film: Film, navHostController: NavHostController) {
        navHostController.navigate(
            "film/${film.id}/${film.Name}/${
                film.Logo.replace(
                    "/",
                    " "
                )
            }/${Gson().toJson(film.tags)}/${film.description}/${film.ageRate}"
        )
    }

}