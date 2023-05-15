package io.github.drkv333.lookforward

import android.content.SharedPreferences
import androidx.core.content.edit
import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder
import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.model.HolidayKind
import io.github.drkv333.lookforward.network.CalendarificService
import io.github.drkv333.lookforward.persistence.HolidayDao
import io.github.drkv333.lookforward.ui.login.LoginViewModel
import io.github.drkv333.lookforward.ui.main.MainRepository
import io.github.drkv333.lookforward.ui.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.util.*

class MainTests {
    @get:Rule
    @OptIn(ExperimentalCoroutinesApi::class)
    val mainDispatcherRule = MainDispatcherRule()

    @MockK(relaxed = true)
    lateinit var mockCalendarificService: CalendarificService

    @MockK(relaxed = true)
    lateinit var mockHolidayDao: HolidayDao

    @MockK(relaxed = true)
    lateinit var mockMainRepository: MainRepository

    lateinit var mockSharedPreferences: SharedPreferences

    @Before
    fun setup() {
        mockSharedPreferences = SPMockBuilder().createSharedPreferences()
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `LoadHolidays returns local holidays if available`() = runTest {
        val testHoliday = Holiday("test", "test", HolidayKind.OTHER, Calendar.getInstance().apply { add(Calendar.YEAR, 1) }.time)
        coEvery { mockHolidayDao.getAll() } returns arrayOf(testHoliday)

        val mainRepository = MainRepository(mockCalendarificService, mockHolidayDao)
        val result = mainRepository.loadHolidayList()

        assert(result.size == 1 && result[0].id == testHoliday.id)
        coVerify { mockHolidayDao.getAll() }
        coVerify { mockCalendarificService.holidaysGet(any(), any()) wasNot called }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `LoadHolidays returns holidays from API if not available locally`() = runTest {
        val testHoliday = holidayItem("test holiday item", "test", "other", Calendar.getInstance().apply { add(Calendar.YEAR, 1) }.get(Calendar.YEAR), 1, 1)
        coEvery { mockCalendarificService.holidaysGet(any(), any()) } returns Response.success(holidayResponseOf(testHoliday))

        val mainRepository = MainRepository(mockCalendarificService, mockHolidayDao)
        val result = mainRepository.loadHolidayList()

        assert(result.size == 1 && result[0].title == testHoliday.name)
        coVerify { mockCalendarificService.holidaysGet(any(), any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Main VM gets holiday details from repository`() = runTest {
        val testHoliday = Holiday("test", "test", HolidayKind.OTHER, Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, 2) }.time)
        coEvery { mockMainRepository.loadHolidayList() } returns listOf(testHoliday)

        val mainViewModel = MainViewModel(mockSharedPreferences, mockMainRepository)
        mainDispatcherRule.runCurrent()

        assert(mainViewModel.holidayList.size == 1 && mainViewModel.holidayList[0].id == testHoliday.id)
        assert(mainViewModel.firstHoliday?.id == testHoliday.id)
        assert(mainViewModel.firstHolidayTimeLeft > 0L)
    }

    @Test
    fun `Logout works`() {
        mockSharedPreferences.edit {
            putBoolean(LoginViewModel.IS_LOGGED_IN_KEY, true)
        }

        val mainViewModel = MainViewModel(mockSharedPreferences, mockMainRepository)
        mainViewModel.logout()

        assert(!mockSharedPreferences.getBoolean(LoginViewModel.IS_LOGGED_IN_KEY, true))
    }
}