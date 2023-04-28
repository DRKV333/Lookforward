package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponseResponseDate (
    val iso: String,
    val datetime: HolidayResponseResponseDateDatetime
)