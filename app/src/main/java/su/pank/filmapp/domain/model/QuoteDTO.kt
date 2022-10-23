package su.pank.filmapp.domain.model

import com.google.gson.annotations.SerializedName

data class QuoteDTO(@SerializedName("quote_id") val id: Int, val quote: String)