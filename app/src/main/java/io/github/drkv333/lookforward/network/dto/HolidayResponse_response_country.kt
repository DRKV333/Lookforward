package io.github.drkv333.lookforward.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponseResponseCountry (
    val id: String,
    val name: String
)