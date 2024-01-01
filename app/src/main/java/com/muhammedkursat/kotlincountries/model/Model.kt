package com.muhammedkursat.kotlincountries.model

import android.media.Image
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName: String?,
    @SerializedName("region")
    val countryRegion: String?,
    @SerializedName("capital")
    val countryCapital: String?,
    @SerializedName("flag")
    val countryFlag: String?,
    @SerializedName("currency")
    val countryCurrency: String?,
    @SerializedName("language")
    val countryLanguage: String?
)