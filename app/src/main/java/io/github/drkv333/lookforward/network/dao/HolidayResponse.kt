package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponse (
    val meta: CountryResponseMeta,
    val response: HolidayResponseResponse
)