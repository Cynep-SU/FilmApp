package su.pank.filmapp.presentation.main_screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import org.koin.androidx.compose.get
import su.pank.filmapp.domain.model.Film
import su.pank.filmapp.domain.viewmodel.FilmsViewModel
import su.pank.filmapp.domain.viewmodel.Status


@Composable
fun Recommendation(navHostController: NavHostController, model: FilmsViewModel = viewModel()) {
    Box() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(model.recommendFilm.Logo).crossfade(true).build(),
            contentDescription = model.recommendFilm.Name,
            Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        // Затемнение, довольно весело это сделано в Compose
        Box(modifier = Modifier
            .matchParentSize()
            .background(Brush.linearGradient(listOf(Color.Transparent, Color.Black))) )
        Button(
            onClick = { model.openFilm(model.recommendFilm, navHostController) }, modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 40.dp)
                .align(
                    Alignment.BottomCenter
                )
        ) {
            Text(text = "Смотреть")
        }
    }
}


@Composable
fun FilmsRow(name: String, filmList: List<Film>, navHostController: NavHostController) {
    val model: FilmsViewModel = viewModel()
    Column(modifier = Modifier.padding(10.dp)) {
        Text(name, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(10.dp))
        LazyRow {
            items(filmList) { film: Film ->
                Image(
                    painter = rememberAsyncImagePainter(film.Logo),
                    contentDescription = film.Name,
                    modifier = Modifier
                        .size(100.dp, 144.dp)
                        .padding(0.dp, 0.dp, 10.dp, 0.dp).clickable { model.openFilm(film, navHostController) },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun BigFilmsRow(name: String, filmList: List<Film>, navHostController: NavHostController) {
    val model: FilmsViewModel = viewModel()
    Column(modifier = Modifier.padding(10.dp)) {
        Text(name, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(10.dp))
        LazyRow {
            items(filmList) { film: Film ->
                Image(
                    painter = rememberAsyncImagePainter(film.Logo),
                    contentDescription = film.Name,
                    modifier = Modifier
                        .size(240.dp, 144.dp)
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        .clickable { model.openFilm(film, navHostController) },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun VideoPlayer(source: String) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(source))

            this.prepare()
        }
    }
    AndroidView(
        { context ->
            StyledPlayerView(context).apply {
                player = exoPlayer
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    )

}


@Composable
fun Watched(model: FilmsViewModel = viewModel()) {
    Text(
        "Вы смотрели:",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(20.dp)
    )
    VideoPlayer(source = model.watched)

}


@Composable
fun GeneralScreen(navHostController: NavHostController) {
    val filmsViewModel: FilmsViewModel = get()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(filmsViewModel.scrollState)
    ) {

        if (filmsViewModel.status == Status.Success) {
            Recommendation(navHostController, filmsViewModel)
            FilmsRow(name = "В тренде", filmList = filmsViewModel.trendingFilms, navHostController)
            Watched(filmsViewModel)
            BigFilmsRow(name = "Новое", filmList = filmsViewModel.newFilms, navHostController)
            FilmsRow(name = "Для вас", filmList = filmsViewModel.forYou, navHostController)
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Указать интересы")
            }
        }
    }
}
