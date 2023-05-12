package io.github.drkv333.lookforward.ui.main

import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.model.toModel
import io.github.drkv333.lookforward.network.CalendarificService
import io.github.drkv333.lookforward.persistence.HolidayDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.util.Calendar
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val calendarificService: CalendarificService,
    private val holidayDao: HolidayDao
) {
    fun loadHolidayList(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow<List<Holiday>> {
        var holidays = holidayDao.getAll()
        if (holidays.isEmpty()) {
            val response = calendarificService.holidaysGet("HU", 2023)
            if (response.isSuccessful) {
                holidays = response.body()!!.response.holidays.map { it.toModel() }.toTypedArray()
                holidayDao.insert(*holidays)
            } else {
                onError(response.message())
            }
        }

        emit(holidays.filter { it.date.after(Calendar.getInstance().time) })
    }.onStart { onStart() }
        .onCompletion { onCompletion() }
        .flowOn(Dispatchers.IO)
}