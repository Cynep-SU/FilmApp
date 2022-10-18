package su.pank.filmapp.domain.model

import com.google.gson.annotations.SerializedName
import su.pank.filmapp.domain.CONSTANTS

data class Film(
    @SerializedName("char_id") val id: Int,
    @SerializedName("name") val Name: String,
    @SerializedName("img") val Logo: String,
    @SerializedName("occupation") val tags: List<String>,
    val description: String = "",
    val ageRate: String = CONSTANTS.ageRates.keys.random()
)
