package io.github.drkv333.lookforward.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.drkv333.lookforward.network.dto.HolidayResponseResponseHolidays
import java.util.*

@Entity(tableName = "holiday")
data class Holiday(
    val title: String,
    val description: String,
    val date: Date,
    @PrimaryKey val id: UUID = UUID.randomUUID(),
)

fun HolidayResponseResponseHolidays.toModel() = Holiday(
    title = name,
    description = description,
    date = Calendar.getInstance().apply {
        set(Calendar.YEAR, date.datetime.year)
        set(Calendar.MONTH, date.datetime.month)
        set(Calendar.DAY_OF_MONTH, date.datetime.day)
    }.time
)