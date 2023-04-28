package io.github.drkv333.lookforward.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponseResponseDate (
    val iso: String,
    val datetime: HolidayResponseResponseDateDatetime
)