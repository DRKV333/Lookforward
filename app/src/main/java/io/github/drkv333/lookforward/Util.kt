package io.github.drkv333.lookforward

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
    }
}

fun NavController.navigateReplace(route: String) {
    navigate(route) {
        popUpToTop(this@navigateReplace)
    }
}