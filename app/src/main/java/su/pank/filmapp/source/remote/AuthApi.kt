package su.pank.filmapp.source.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface AuthApi {
    @GET("/ok")
    suspend fun ok(): ResponseBody
}