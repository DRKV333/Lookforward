package io.github.drkv333.lookforward.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.model.HolidayKind
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val detailsRepository: DetailsRepository
) : ViewModel() {
    val id: UUID = UUID.fromString(savedStateHandle["id"])

    var loading by mutableStateOf(true)

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var kind by mutableStateOf(HolidayKind.OTHER)
    var date by mutableStateOf(Date())

    init {
        viewModelScope.launch {
            val holiday = detailsRepository.loadHolidayById(id)
            title = holiday.title
            description = holiday.description
            kind = holiday.kind
            date = holiday.date
            loading = false
        }
    }

    private fun makeHoliday() = Holiday(title, description, kind, date, id)

    fun save() {
        viewModelScope.launch {
            detailsRepository.saveHoliday(makeHoliday())
        }
    }

    fun delete() {
        viewModelScope.launch {
            detailsRepository.deleteHoliday(makeHoliday())
        }
    }
}