package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse (
    val meta: CountryResponseMeta,
    val response: CountryResponseResponse
)