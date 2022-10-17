package su.pank.filmapp.domain.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import su.pank.filmapp.domain.model.Film
import su.pank.filmapp.source.remote.BreakingBadApi

enum class Status {
    Loaded,
    Success
}

class FilmsViewModel : ViewModel() {
    var status = mutableStateOf(Status.Loaded)
    lateinit var recommendFilm: Film
    lateinit var trendingFilms: List<Film>
    lateinit var newFilms: List<Film>
    lateinit var forYou: List<Film>
    val watched = "https://github.com/Cynep-SU/FilmApp/blob/master/app/src/main/res/drawable-v24/saul.mp4?raw=true"
    private lateinit var films: List<Film>;

    init {
        val filmApi: BreakingBadApi = get(BreakingBadApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val getRec = async {
                recommendFilm = filmApi.getRandomCharacter(1)[0]
            }
            val getForYou = async {
                forYou = filmApi.getRandomCharacter(10)
            }
            val getNew = async {
                newFilms = filmApi.getRandomCharacter(10)
            }
            films = filmApi.getAllCharacters()
            trendingFilms = films
            getNew.await()
            getForYou.await()
            getRec.await()
            status.value = Status.Success
        }
    }
}