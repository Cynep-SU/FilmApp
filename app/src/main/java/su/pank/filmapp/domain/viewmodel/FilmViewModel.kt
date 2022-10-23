package su.pank.filmapp.domain.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get
import su.pank.filmapp.domain.CONSTANTS
import su.pank.filmapp.domain.model.Film
import su.pank.filmapp.source.remote.BreakingBadApi

class FilmViewModel() : ViewModel() {
    var description by mutableStateOf("")
    val videoPlayer = ExoPlayer.Builder(get(Context::class.java)).build().apply {
        setMediaItem(MediaItem.fromUri(CONSTANTS.SOUL_GOODMAN))
        this.prepare()
    }


    fun loadDescription(film: Film) {
        CoroutineScope(Dispatchers.IO).launch {
            val breakingBadApi: BreakingBadApi = get(BreakingBadApi::class.java)
            description = breakingBadApi.getQuoteByCharacter(film.Name)
                .joinToString("\n") { quoteDTO -> quoteDTO.quote }
            if (description == "") {
                description = "Description is not founded"
            }
        }
    }
}