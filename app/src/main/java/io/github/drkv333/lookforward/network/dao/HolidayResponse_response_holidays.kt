package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponseResponseHolidays (
    val name: String,
    val description: String,
    val country: HolidayResponseResponseCountry,
    val date: HolidayResponseResponseDate,
    val type: Array<String>,
    @SerialName("primary_type") val primaryType: String,
    @SerialName("canonical_url") val canonicalUrl: String,
    val urlid: String,
    val locations: String,
    val states: String
)