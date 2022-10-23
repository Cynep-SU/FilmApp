package su.pank.filmapp.source.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import su.pank.filmapp.domain.model.Film
import su.pank.filmapp.domain.model.QuoteDTO

interface BreakingBadApi {
    @GET("characters")
    suspend fun getAllCharacters(): List<Film>

    @GET("character/random")
    suspend fun getRandomCharacter(@Query("limit") limit: Int = 1): List<Film>

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): List<Film>

    @GET("quote")
    suspend fun getQuoteByCharacter(@Query("author") author: String): List<QuoteDTO>
}