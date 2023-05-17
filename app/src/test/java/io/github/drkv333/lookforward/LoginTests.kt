package io.github.drkv333.lookforward

import android.content.SharedPreferences
import androidx.core.content.edit
import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder
import io.github.drkv333.lookforward.ui.login.LoginViewModel
import org.junit.Before
import org.junit.Test

class LoginTests {
    lateinit var mockSharedPreferences: SharedPreferences

    @Before
    fun setup() {
        mockSharedPreferences = SPMockBuilder().createSharedPreferences()
    }

    @Test
    fun `Successful login sets shared pref`() {
        val loginViewModel = LoginViewModel(mockSharedPreferences)
        loginViewModel.login()

        assert(mockSharedPreferences.getBoolean(LoginViewModel.IS_LOGGED_IN_KEY, false))
    }

    @Test
    fun `Autologin succeeds when shared pref is set`() {
        mockSharedPreferences.edit {
            putBoolean(LoginViewModel.IS_LOGGED_IN_KEY, true)
        }

        val loginViewModel = LoginViewModel(mockSharedPreferences)

        assert(loginViewModel.autoLogin())
    }
}