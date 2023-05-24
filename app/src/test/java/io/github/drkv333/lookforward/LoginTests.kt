package io.github.drkv333.lookforward

import android.content.SharedPreferences
import androidx.core.content.edit
import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder
import com.google.firebase.analytics.FirebaseAnalytics
import io.github.drkv333.lookforward.ui.login.LoginViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class LoginTests {
    lateinit var mockSharedPreferences: SharedPreferences

    @MockK(relaxed = true)
    lateinit var mockAnalytics: FirebaseAnalytics

    @Before
    fun setup() {
        mockSharedPreferences = SPMockBuilder().createSharedPreferences()
        MockKAnnotations.init(this)
    }

    @Test
    fun `Successful login sets shared pref`() {
        val loginViewModel = LoginViewModel(mockSharedPreferences, mockAnalytics)
        loginViewModel.login()

        assert(mockSharedPreferences.getBoolean(LoginViewModel.IS_LOGGED_IN_KEY, false))
    }

    @Test
    fun `Autologin succeeds when shared pref is set`() {
        mockSharedPreferences.edit {
            putBoolean(LoginViewModel.IS_LOGGED_IN_KEY, true)
        }

        val loginViewModel = LoginViewModel(mockSharedPreferences, mockAnalytics)

        assert(loginViewModel.autoLogin())
    }
}