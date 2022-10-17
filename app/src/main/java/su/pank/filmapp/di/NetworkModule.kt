package su.pank.filmapp.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import su.pank.filmapp.domain.CONSTANTS
import su.pank.filmapp.source.remote.AuthApi
import su.pank.filmapp.source.remote.BreakingBadApi

val networkModule = module {
    single { getAuthApi() }
    single { getBreakingBadApi() }
}

fun getAuthApi(): AuthApi {
    return Retrofit.Builder().baseUrl(CONSTANTS.AUTH_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(AuthApi::class.java)
}

fun getBreakingBadApi(): BreakingBadApi {
    return Retrofit.Builder().baseUrl(CONSTANTS.BD_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(BreakingBadApi::class.java)
}
