package io.github.drkv333.lookforward.ui.main

import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.model.toModel
import io.github.drkv333.lookforward.network.CalendarificService
import io.github.drkv333.lookforward.persistence.HolidayDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val calendarificService: CalendarificService,
    private val holidayDao: HolidayDao
) {
    suspend fun loadHolidayList(): List<Holiday> = withContext(Dispatchers.IO) {
        var holidays = holidayDao.getAll()
        if (holidays.isEmpty()) {
            val response = calendarificService.holidaysGet("HU", 2023)
            if (response.isSuccessful) {
                holidays = response.body()!!.response.holidays.map { it.toModel() }.toTypedArray()
                holidayDao.insert(*holidays)
            }
        }

        return@withContext holidays.filter { it.date.after(Calendar.getInstance().time) }
    }
}