package io.github.drkv333.lookforward.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.persistence.HolidayDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val holidayDao: HolidayDao
) : ViewModel() {
    // TODO: This is just for testing.
    val holidayList = mainRepository.loadHolidayList(
        {}, {}, {}
    )

    suspend fun insertTest() {
        withContext(Dispatchers.IO) {
            holidayDao.insert(Holiday("test", "test", Date()))
        }
    }
}