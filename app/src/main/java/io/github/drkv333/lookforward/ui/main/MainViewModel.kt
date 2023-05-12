package io.github.drkv333.lookforward.ui.main

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.ui.login.LoginViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    mainRepository: MainRepository
) : ViewModel() {
    var loading by mutableStateOf(true)

    var holidayList by mutableStateOf(listOf<Holiday>())

    var firstHoliday by mutableStateOf<Holiday?>(null)
    var firstHolidayTimeLeft by mutableStateOf(0L)

    init {
        viewModelScope.launch {
            holidayList = mainRepository.loadHolidayList()
            firstHoliday = holidayList.firstOrNull()
            firstHoliday?.let {
                firstHolidayTimeLeft =  (it.date.time - Calendar.getInstance().time.time) / (1000 * 60 * 60 * 24)
            }
            loading = false
        }
    }

    val isLoggedIn = sharedPreferences.getBoolean(LoginViewModel.IS_LOGGED_IN_KEY, false)

    fun logout() {
        sharedPreferences.edit {
            putBoolean(LoginViewModel.IS_LOGGED_IN_KEY, false)
        }
    }
}