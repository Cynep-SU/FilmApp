package su.pank.filmapp.domain.model

import com.google.gson.annotations.SerializedName

data class Film(@SerializedName("char_id") val id: Int, @SerializedName("name") val Name: String,@SerializedName("img") val Logo: String)
