package io.github.drkv333.lookforward

import io.github.drkv333.lookforward.network.dto.*

fun holidayItem(name: String, desc: String, type: String, year: Int, month: Int, day: Int) = HolidayResponseResponseHolidays(
    name = name,
    description = desc,
    country = HolidayResponseResponseCountry("HU", "Hungary"),
    date = HolidayResponseResponseDate("$year-$month-$day", HolidayResponseResponseDateDatetime(year, month, day)),
    type = arrayOf(type),
    primaryType = type,
    canonicalUrl = "",
    urlid = "",
    locations = "Hungary",
    states = "Hungary"
)

fun holidayResponseOf(vararg responses: HolidayResponseResponseHolidays) = HolidayResponse(
    CountryResponseMeta(200),
    HolidayResponseResponse(arrayOf(*responses))
)