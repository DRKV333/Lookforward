package io.github.drkv333.lookforward.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.github.drkv333.lookforward.ui.details.Details
import io.github.drkv333.lookforward.ui.login.Login
import io.github.drkv333.lookforward.ui.main.Main
import io.github.drkv333.lookforward.ui.theme.LookforwardTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LookforwardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { Login(navController) }
                        composable("main") { Main(navController) }
                        composable(
                            "details?id={id}",
                            arguments = listOf(navArgument("id") { defaultValue = "" })
                        ) {
                            Details(navController)
                        }
                    }
                }
            }
        }
    }
}
