package io.github.drkv333.lookforward.ui.main

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.drkv333.lookforward.model.Holiday
import io.github.drkv333.lookforward.persistence.HolidayDao
import io.github.drkv333.lookforward.ui.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val mainRepository: MainRepository,
    private val holidayDao: HolidayDao
) : ViewModel() {
    // TODO: This is just for testing.
    val holidayList = mainRepository.loadHolidayList(
        {}, {}, {}
    )

    val isLoggedIn = sharedPreferences.getBoolean(LoginViewModel.IS_LOGGED_IN_KEY, false)

    suspend fun insertTest() {
        withContext(Dispatchers.IO) {
            holidayDao.insert(Holiday("test", "test", Date()))
        }
    }

    fun logout() {
        sharedPreferences.edit {
            putBoolean(LoginViewModel.IS_LOGGED_IN_KEY, false)
        }
    }
}