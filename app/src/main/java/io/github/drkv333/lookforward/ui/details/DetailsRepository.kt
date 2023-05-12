package io.github.drkv333.lookforward.ui.details

import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.persistence.HolidayDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class DetailsRepository(
    private val holidayDao: HolidayDao
) {
    suspend fun loadHolidayById(id: UUID) = withContext(Dispatchers.IO) {
        return@withContext holidayDao.get(id)
    }

    suspend fun saveHoliday(holiday: Holiday) = withContext(Dispatchers.IO) {
        holidayDao.update(holiday)
    }

    suspend fun deleteHoliday(holiday: Holiday) = withContext(Dispatchers.IO) {
        holidayDao.delete(holiday)
    }
}