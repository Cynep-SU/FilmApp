package su.pank.filmapp.di

import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import su.pank.filmapp.domain.viewmodel.LoginViewModel
import su.pank.filmapp.domain.viewmodel.RegViewModel

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegViewModel(get()) }
    single {
        NavHostController(get()).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }
    }
}
