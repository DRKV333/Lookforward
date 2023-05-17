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
    private var id: UUID
    private var isCreating: Boolean

    var loading by mutableStateOf(true)
    var editing by mutableStateOf(false)

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var kind by mutableStateOf(HolidayKind.OTHER)
    var date by mutableStateOf(Date())

    init {
        val idParam: String? = savedStateHandle["id"];
        if (idParam.isNullOrEmpty()) {
            id = UUID.randomUUID()
            isCreating = true
            editing = true

            title = "New Holiday"
            description = ""
            kind = HolidayKind.OTHER
            date = Calendar.getInstance().apply {
                add(Calendar.DAY_OF_MONTH, 1)
            }.time

            loading = false
        } else {
            id = UUID.fromString(idParam)
            isCreating = false
            editing = false

            viewModelScope.launch {
                val holiday = detailsRepository.loadHolidayById(id)
                title = holiday.title
                description = holiday.description
                kind = holiday.kind
                date = holiday.date
                loading = false
            }
        }
    }

    private fun makeHoliday() = Holiday(title, description, kind, date, id)

    fun save() {
        viewModelScope.launch {
            if (isCreating) {
                isCreating = false
                detailsRepository.createHoliday(makeHoliday())
            } else {
                detailsRepository.saveHoliday(makeHoliday())
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            if (!isCreating)
                detailsRepository.deleteHoliday(makeHoliday())
        }
    }
}