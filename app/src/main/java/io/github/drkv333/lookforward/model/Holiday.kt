package io.github.drkv333.lookforward.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.drkv333.lookforward.network.dto.HolidayResponseResponseHolidays
import java.util.*
import io.github.drkv333.lookforward.R

enum class HolidayKind {
    NATIONAL,
    OBSERVANCE,
    SEASON,
    OTHER
}

val HolidayKind.iconResource
    get() = when(this) {
        HolidayKind.NATIONAL -> R.drawable.nationalicon
        HolidayKind.OBSERVANCE -> R.drawable.observanceicon
        HolidayKind.SEASON -> R.drawable.seasonicon
        HolidayKind.OTHER -> R.drawable.othericon
    }

@Entity(tableName = "holiday")
data class Holiday(
    val title: String,
    val description: String,
    val kind: HolidayKind,
    val date: Date,
    @PrimaryKey val id: UUID = UUID.randomUUID(),
)

fun HolidayResponseResponseHolidays.toModel() = Holiday(
    title = name,
    description = description,
    kind = when(primaryType) {
        "National holiday" -> HolidayKind.NATIONAL
        "Observance" -> HolidayKind.OBSERVANCE
        "Season" -> HolidayKind.SEASON
        else -> HolidayKind.OTHER
    },
    date = Calendar.getInstance().apply {
        set(Calendar.YEAR, date.datetime.year)
        set(Calendar.MONTH, date.datetime.month)
        set(Calendar.DAY_OF_MONTH, date.datetime.day)
    }.time
)