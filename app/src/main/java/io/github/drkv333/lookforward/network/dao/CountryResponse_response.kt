package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponseResponse (
    val url: String,
    val countries: Array<CountryResponseResponseCountries>
)