package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponseResponseCountries (
    val countryName: String,
    val iso3166: String,
    val totalHolidays: Int,
    val supportedLanguages: Int,
    val uuid: String
)