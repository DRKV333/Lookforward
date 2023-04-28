package io.github.drkv333.lookforward.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponse (
    val meta: CountryResponseMeta,
    val response: HolidayResponseResponse
)