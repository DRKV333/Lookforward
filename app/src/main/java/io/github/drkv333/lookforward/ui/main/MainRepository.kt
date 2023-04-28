package io.github.drkv333.lookforward.ui.main

import io.github.drkv333.lookforward.network.CalendarificService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val calendarificService: CalendarificService
) {
    fun loadHolidayList(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val response = calendarificService.holidaysGet("HU", 2023)
        if (response.isSuccessful) {
            emit(response.body()!!)
        } else {
            onError(response.message())
        }
    }.onStart { onStart() }
        .onCompletion { onCompletion() }
        .flowOn(Dispatchers.IO)
}