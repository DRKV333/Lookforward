package io.github.drkv333.lookforward.ui.main

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.drkv333.lookforward.persistence.HolidayDao
import io.github.drkv333.lookforward.ui.login.LoginViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val mainRepository: MainRepository,
    private val holidayDao: HolidayDao
) : ViewModel() {
    var loading by mutableStateOf(true)

    val holidayList = mainRepository.loadHolidayList(
        onStart = {},
        onCompletion = { loading = false },
        onError = {}
    )

    val firstHoliday = holidayList.mapNotNull { it.firstOrNull() }
    val firstHolidayTimeLeft = firstHoliday.map { (it.date.time - Calendar.getInstance().time.time) / (1000 * 60 * 60 * 24) }

    val isLoggedIn = sharedPreferences.getBoolean(LoginViewModel.IS_LOGGED_IN_KEY, false)

    fun logout() {
        sharedPreferences.edit {
            putBoolean(LoginViewModel.IS_LOGGED_IN_KEY, false)
        }
    }
}