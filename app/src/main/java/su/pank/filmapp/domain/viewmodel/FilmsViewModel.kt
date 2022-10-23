package su.pank.filmapp.domain.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
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
    val scrollState = LazyListState(
        0,
        0
    )
    lateinit var recommendFilm: Film
    lateinit var trendingFilms: List<Film>
    lateinit var newFilms: List<Film>
    lateinit var forYou: List<Film>
    val watched = "https://github.com/Cynep-SU/FilmApp/blob/master/videos/saul.mp4?raw=true"
    val videoPlayer = ExoPlayer.Builder(get(Context::class.java)).build().apply {
        setMediaItem(MediaItem.fromUri(watched))
        this.prepare()
    }
    private lateinit var films: List<Film>

    init {
        println("WTF")
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