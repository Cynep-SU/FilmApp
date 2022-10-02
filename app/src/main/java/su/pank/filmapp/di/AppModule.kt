package su.pank.filmapp.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import su.pank.filmapp.domain.viewmodel.LoginViewModel

val appModule = module {
    viewModel { LoginViewModel(get()) }
}
