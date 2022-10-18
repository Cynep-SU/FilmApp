package su.pank.filmapp.presentation.main_screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import su.pank.filmapp.domain.model.Film
import su.pank.filmapp.presentation.theme.FilmAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmScreen(film: Film) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = film.Name) },
            navigationIcon = { Icon(Icons.Default.ArrowBack, null) },
            actions = {
                Modifier.padding(10.dp)
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null
                )
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null
                )
            })
    }) {

    }
}

@Preview
@Composable
fun PreviewFilmScreen() {
    FilmAppTheme {
        FilmScreen(film = Film(0, "test", "test"))
    }
}