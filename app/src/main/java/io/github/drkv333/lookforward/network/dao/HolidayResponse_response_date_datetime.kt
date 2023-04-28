package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponseResponseDateDatetime (
    val year: Int,
    val month: Int,
    val day: Int
)