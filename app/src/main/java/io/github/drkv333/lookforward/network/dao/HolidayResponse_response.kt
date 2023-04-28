package io.github.drkv333.lookforward.network.dao

import kotlinx.serialization.Serializable

@Serializable
data class HolidayResponseResponse (
    val holidays: Array<HolidayResponseResponseHolidays>
)