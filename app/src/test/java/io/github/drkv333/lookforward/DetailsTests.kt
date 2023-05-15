package io.github.drkv333.lookforward

import androidx.lifecycle.SavedStateHandle
import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.model.HolidayKind
import io.github.drkv333.lookforward.persistence.HolidayDao
import io.github.drkv333.lookforward.ui.details.DetailsRepository
import io.github.drkv333.lookforward.ui.details.DetailsViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class DetailsTests {
    @get:Rule
    @OptIn(ExperimentalCoroutinesApi::class)
    val mainDispatcherRule = MainDispatcherRule()

    @MockK(relaxed = true)
    lateinit var mockHolidayDao: HolidayDao

    @MockK(relaxed = true)
    lateinit var mockDetailsRepository: DetailsRepository

    @MockK(relaxed = true)
    lateinit var mockSavedStateHandle: SavedStateHandle

    @Before
    fun setup() = MockKAnnotations.init(this)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Repository gets holiday from DAO`() = runTest {
        val testHoliday = Holiday("test", "test", HolidayKind.OTHER, Date())
        coEvery { mockHolidayDao.get(testHoliday.id) } returns testHoliday

        val detailsRepository = DetailsRepository(mockHolidayDao)

        assert(detailsRepository.loadHolidayById(testHoliday.id).id == testHoliday.id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Repository updates holiday in DAO`() = runTest {
        val testHoliday = Holiday("test", "test", HolidayKind.OTHER, Date())

        val detailsRepository = DetailsRepository(mockHolidayDao)
        detailsRepository.saveHoliday(testHoliday)

        coVerify { mockHolidayDao.update(testHoliday) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Repository inserts holiday in DAO`() = runTest {
        val testHoliday = Holiday("test", "test", HolidayKind.OTHER, Date())

        val detailsRepository = DetailsRepository(mockHolidayDao)
        detailsRepository.createHoliday(testHoliday)

        coVerify { mockHolidayDao.insert(testHoliday) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Repository deletes holiday in DAO`() = runTest {
        val testHoliday = Holiday("test", "test", HolidayKind.OTHER, Date())

        val detailsRepository = DetailsRepository(mockHolidayDao)
        detailsRepository.deleteHoliday(testHoliday)

        coVerify { mockHolidayDao.delete(testHoliday) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Details VM edits existing holiday`() = runTest {
        val testHoliday = Holiday("test", "test", HolidayKind.OTHER, Date())
        coEvery { mockDetailsRepository.loadHolidayById(testHoliday.id) } returns testHoliday
        every { mockSavedStateHandle.get<String>("id") } returns testHoliday.id.toString()

        val detailsViewModel = DetailsViewModel(mockSavedStateHandle, mockDetailsRepository)
        mainDispatcherRule.runCurrent()

        assert(detailsViewModel.title == "test")
        assert(!detailsViewModel.editing)

        val modifiedTitle = "modified title"
        detailsViewModel.title = modifiedTitle
        detailsViewModel.save()
        mainDispatcherRule.runCurrent()

        val modifiedHoliday = slot<Holiday>()
        coVerify { mockDetailsRepository.saveHoliday(capture(modifiedHoliday)) }
        assert(modifiedHoliday.captured.title == modifiedTitle)
        assert(modifiedHoliday.captured.id == testHoliday.id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Details VM deletes existing holiday`() = runTest {
        val testHoliday = Holiday("test", "test", HolidayKind.OTHER, Date())
        coEvery { mockDetailsRepository.loadHolidayById(testHoliday.id) } returns testHoliday
        every { mockSavedStateHandle.get<String>("id") } returns testHoliday.id.toString()

        val detailsViewModel = DetailsViewModel(mockSavedStateHandle, mockDetailsRepository)
        mainDispatcherRule.runCurrent()

        detailsViewModel.delete()
        mainDispatcherRule.runCurrent()

        coVerify { mockDetailsRepository.deleteHoliday(withArg { holiday -> assert(holiday.id == testHoliday.id) }) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Details VM creates new holiday`() = runTest {
        every { mockSavedStateHandle.get<String>("id") } returns null

        val detailsViewModel = DetailsViewModel(mockSavedStateHandle, mockDetailsRepository)
        mainDispatcherRule.runCurrent()

        detailsViewModel.save()
        mainDispatcherRule.runCurrent()

        coVerify { mockDetailsRepository.createHoliday(any()) }
    }
}
