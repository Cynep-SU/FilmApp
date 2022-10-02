package su.pank.filmapp.di

import org.koin.dsl.module
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import su.pank.filmapp.domain.CONSTANTS
import su.pank.filmapp.source.remote.AuthApi

val networkModule = module {
    single { Retrofit.Builder().baseUrl(CONSTANTS.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build() }
    single { getAuthApi(get()) }
}

fun getAuthApi(retrofit: Retrofit) : AuthApi{
    return retrofit.create(AuthApi::class.java)
}

