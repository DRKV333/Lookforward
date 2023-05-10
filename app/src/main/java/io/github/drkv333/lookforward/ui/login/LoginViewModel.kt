package io.github.drkv333.lookforward.ui.login

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    companion object {
        private val IS_LOGGED_IN_KEY = "IS_LOGGED_IN"
    }

    var username by mutableStateOf("")
    var password by mutableStateOf("")

    fun autoLogin(): Boolean = sharedPreferences.getBoolean(IS_LOGGED_IN_KEY, false)

    fun login(): Boolean {
        sharedPreferences.edit {
            putBoolean(IS_LOGGED_IN_KEY, true)
        }
        // TODO: Login with the Lookforward cloud sync API
        return true
    }
}