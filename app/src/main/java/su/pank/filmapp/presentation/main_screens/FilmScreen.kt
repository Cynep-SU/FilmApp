package su.pank.filmapp.presentation.main_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.koin.java.KoinJavaComponent.get
import su.pank.filmapp.R
import su.pank.filmapp.domain.CONSTANTS
import su.pank.filmapp.domain.model.Film
import su.pank.filmapp.domain.viewmodel.FilmsViewModel
import su.pank.filmapp.presentation.theme.FilmAppTheme
import su.pank.filmapp.source.remote.BreakingBadApi

// TODO переделать под json навигацию, хз как, но надо
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmScreen(filmId: Int){
    var film: Film? = null
    var filmFounded by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(null){
        val breakingBadApi: BreakingBadApi = get(BreakingBadApi::class.java)
        film = breakingBadApi.getCharacterById(filmId)[0]
        filmFounded = true
    }
    if (filmFounded)
        FilmScreen(film = film!!)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmScreen(film: Film) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = film.Name) },
            navigationIcon = { Icon(Icons.Outlined.KeyboardArrowLeft, null, Modifier.size(30.dp)) },
            actions = {
                Modifier.padding(10.dp)
                Text(
                    text = film.ageRate,
                    modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp),
                    color = CONSTANTS.ageRates[film.ageRate]!!,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    painterResource(id = R.drawable.comments),
                    contentDescription = null,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                    tint = Color(0xFFEF3A01)
                )
            })
    }) {
        Column(
            Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            logoWithTags(film = film)
            WatchFilm(film = film)
            FilmDescription(film = film)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun logoWithTags(film: Film) {
    Box() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(film.Logo).crossfade(true)
                .build(),
            contentDescription = film.Name,
            Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        // Затемнение, довольно весело это сделано в Compose
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Brush.linearGradient(listOf(Color.Transparent, Color.Black)))
        )
        LazyHorizontalGrid(
            rows = GridCells.Adaptive(minSize = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .height(80.dp)
                .align(Alignment.BottomStart)
                .padding(10.dp),
        ) {
            items(film.tags) { tag ->
                SuggestionChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = tag) },
                    colors = SuggestionChipDefaults.suggestionChipColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    border = SuggestionChipDefaults.suggestionChipBorder(borderWidth = 0.dp)
                )
            }
        }
    }
}

@Composable
fun WatchFilm(film: Film) {
    Text(
        "Смотреть",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(20.dp)
    )
    VideoPlayer(source = CONSTANTS.SOUL_GOODMAN)
}

@Composable
fun FilmDescription(film: Film) {
    Text(
        text = "Описание", style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(20.dp)
    )
    Text(text = film.description, modifier = Modifier.padding(20.dp, 0.dp))
}


@Preview
@Composable
fun PreviewFilmScreen() {
    FilmAppTheme {
        FilmScreen(
            film = Film(
                0,
                "test",
                "https://sun9-83.userapi.com/impg/ARDUrlmffYmGH0mkzkxZG6CEmitTGy6wskVstQ/4FRA5X2JLq4.jpg?size=960x1280&quality=95&sign=c139b9f7c65048eff8b8dcd84904145d&type=album",
                listOf("Вася", "Панков", "Программист", "Крутой"),
                "Бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла-бла"
            )
        )
    }
}