package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponseResponseCountry (
    val id: String,
    val name: String
)