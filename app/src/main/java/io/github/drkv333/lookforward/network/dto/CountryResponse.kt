package io.github.drkv333.lookforward.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse (
    val meta: CountryResponseMeta,
    val response: CountryResponseResponse
)