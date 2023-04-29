package io.github.drkv333.lookforward

import io.github.drkv333.lookforward.network.CalendarificService
import io.github.drkv333.lookforward.network.dto.*
import io.github.drkv333.lookforward.persistence.HolidayDao
import io.github.drkv333.lookforward.ui.main.MainRepository
import io.github.drkv333.lookforward.ui.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class SimpleTests {
    @MockK(relaxed = true)
    lateinit var mockCalendarificService: CalendarificService

    @MockK(relaxed = true)
    lateinit var mockMainRepository: MainRepository

    @MockK(relaxed = true)
    lateinit var mockHolidayDao: HolidayDao

    @Before
    fun setup() = MockKAnnotations.init(this)

    private fun holidayItem(name: String, desc: String, type: String, year: Int, month: Int, day: Int) = HolidayResponseResponseHolidays(
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

    private fun holidayResponseOf(vararg responses: HolidayResponseResponseHolidays) = HolidayResponse(
        CountryResponseMeta(200),
        HolidayResponseResponse(arrayOf(*responses))
    )

    @Test
    fun `Main repo gets holidays from API`() = runTest {
        val nameOfHoliday = "New Year's day"
        coEvery { mockCalendarificService.holidaysGet(any(), any()) } returns Response.success(200, holidayResponseOf(
            holidayItem(nameOfHoliday, "Lorem Ipsum", "holiday", 2024, 1, 1)
        ))

        val mainRepository = MainRepository(mockCalendarificService)
        val result = mainRepository.loadHolidayList({}, {}, {}).first()

        assert(result.response.holidays[0].name == nameOfHoliday)
    }

    @Test
    fun `Main view model can create test items in database`() = runTest {
        val mainViewModel = MainViewModel(mockMainRepository, mockHolidayDao)
        mainViewModel.insertTest()

        coVerify { mockHolidayDao.insert(any()) }
    }
}