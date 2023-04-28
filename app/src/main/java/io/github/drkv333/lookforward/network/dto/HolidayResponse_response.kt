package io.github.drkv333.lookforward.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponseResponse (
    val holidays: Array<HolidayResponseResponseHolidays>
)